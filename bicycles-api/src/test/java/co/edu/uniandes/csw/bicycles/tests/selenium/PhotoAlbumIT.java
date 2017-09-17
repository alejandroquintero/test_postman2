/*
The MIT License (MIT)
Copyright (c) 2015 Los Andes University
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.bicycles.tests.selenium;

import co.edu.uniandes.csw.bicycles.dtos.minimum.PhotoAlbumDTO;
import co.edu.uniandes.csw.bicycles.dtos.minimum.BicycleDTO;
import co.edu.uniandes.csw.bicycles.resources.PhotoAlbumResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.arquillian.container.test.api.RunAsClient;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import static org.jboss.arquillian.graphene.Graphene.waitModel;
import org.junit.After;

@RunWith(Arquillian.class)
public class PhotoAlbumIT {

    @ArquillianResource
    private URL deploymentURL;

    @Drone
    private WebDriver driver;

    private static PodamFactory factory = new PodamFactoryImpl();

    private static Properties prop;
    private static InputStream input = null;
    private static final String path = System.getenv("AUTH0_PROPERTIES");

    static {
        prop = new Properties();
        try {
            input = new FileInputStream(path);
            prop.load(input);

        } catch (FileNotFoundException ex) {
           Logger.getAnonymousLogger().info("no se encontro archivo");
        } catch (IOException ex) {
            Logger.getAnonymousLogger().info("no se encontro archivo");
        }

    }

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(PhotoAlbumResource.class.getPackage())
                // archivo de propiedades para autenticacion de auth0
                .addPackage("co.edu.uniandes.csw.auth.properties")
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory("src/main/webapp").as(GenericArchive.class), "/");
    }

    @Before
    public void setup() throws InterruptedException {
        try {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4445/wd/hub"), DesiredCapabilities.chrome()
            );
        } catch (MalformedURLException ex) {
            Logger.getLogger(PhotoAlbumIT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @After
    public void unload() {

        driver.quit();
        
    }

    public void login() throws InterruptedException {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        driver.get(deploymentURL.toExternalForm() + "#/login");
        driver.manage().deleteAllCookies();
        WebElement usernameInput = driver.findElement(By.id("username-input"));
        WebElement passwordInput = driver.findElement(By.id("password-input"));
        WebElement registerBtn = driver.findElement(By.id("log-in-btn"));
        waitModel().until().element(usernameInput).is().visible();
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.sendKeys(prop.getProperty("username").trim());
        passwordInput.sendKeys(prop.getProperty("password").trim());
        registerBtn.click();

    }

    public void createBicycle() throws InterruptedException {

        
login();
        Logger.getAnonymousLogger().info("waiting");
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
       Integer expected = 0;
       Integer countBicycles = driver.findElements(By.cssSelector("tbody > tr")).size();
       Assert.assertEquals(expected,countBicycles);
        WebElement createBtn = driver.findElement(By.id("create-bicycle"));
       waitModel().until().element(createBtn).is().visible();
       createBtn.click();
       BicycleDTO expected_bicycle = factory.manufacturePojo(BicycleDTO.class);
       WebElement nameInput = driver.findElement(By.id("name"));
       WebElement descriptionInput = driver.findElement(By.id("description"));
       WebElement saveBtn = driver.findElement(By.id("save-bicycle"));
       nameInput.clear();
       nameInput.sendKeys(expected_bicycle.getName()); 
       descriptionInput.clear();
       descriptionInput.sendKeys(expected_bicycle.getDescription());  
       saveBtn.click();

    }
  public void deleteBicycle(){
  
  driver.navigate().to(deploymentURL.toExternalForm()+"#/bicycles/list");
         WebElement deleteAnimalBtn = driver.findElement(By.id("0-delete-btn"));
        waitGui().until().element(deleteAnimalBtn).is().visible();
        deleteAnimalBtn.click();
        WebElement confirmAnimalDel = driver.findElement(By.id("confirm-delete"));
       waitGui().until().element(confirmAnimalDel).is().visible();
        confirmAnimalDel.click();
  }
 
    @Test
    @InSequence(0)
    @RunAsClient
    public void createPhotoAlbum() throws InterruptedException {

        createBicycle();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        Logger.getAnonymousLogger().info("waiting");
        PhotoAlbumDTO expected_photo = factory.manufacturePojo(PhotoAlbumDTO.class);
        WebElement createPhotoAnimalBtn = driver.findElement(By.id("photoAlbum-bicycle"));
        waitModel().until().element(createPhotoAnimalBtn).is().visible();
        createPhotoAnimalBtn.click();
         WebElement createPhotoBtn = driver.findElement(By.id("create-photoAlbum"));
        waitModel().until().element(createPhotoBtn).is().visible();
        createPhotoBtn.click();
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement imageInput = driver.findElement(By.id("image"));
        WebElement saveBtn = driver.findElement(By.id("save-photoAlbum"));
        waitModel().until().element(nameInput).is().visible();
        nameInput.clear();
        nameInput.sendKeys(expected_photo.getName());
        imageInput.clear();
        imageInput.sendKeys(expected_photo.getImage());
        saveBtn.click();
        WebElement nameDetail = driver.findElement(By.id("name-detail"));
        waitGui().until().element(nameDetail).is().visible();
        PhotoAlbumDTO actual_photo = new PhotoAlbumDTO();
        actual_photo.setName(nameDetail.getText());
        Assert.assertEquals(expected_photo.getName(),actual_photo.getName());
      
  }
    @Test
   @InSequence(1)
    @RunAsClient
    public void editPhoto() throws InterruptedException {
         login();
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
        Logger.getAnonymousLogger().info("waiting");
        PhotoAlbumDTO expected_photo = factory.manufacturePojo(PhotoAlbumDTO.class);
       
        WebElement bicycleDetail = driver.findElement(By.id("0-detail-btn"));
        waitGui().until().element(bicycleDetail).is().visible();
        bicycleDetail.click();
        WebElement bicyclePhoto = driver.findElement(By.id("photoAlbum-bicycle"));
        waitGui().until().element(bicyclePhoto).is().visible();
        bicyclePhoto.click();
       
        driver.get(deploymentURL.toExternalForm()+"#/bicycles/1/details/photoAlbum/1/edit");
        
         WebElement nameInput = driver.findElement(By.id("name"));
         WebElement imageInput = driver.findElement(By.id("image"));
         waitModel().until().element(nameInput).is().visible();
         waitModel().until().element(imageInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(expected_photo.getName());
        imageInput.clear();
         imageInput.sendKeys(expected_photo.getImage());
         
       WebElement saveBtn = driver.findElement(By.id("save-photoAlbum"));
        waitModel().until().element(saveBtn).is().visible();
       saveBtn.click();
       
        WebElement nameDetail = driver.findElement(By.id("name-detail"));
        waitGui().until().element(nameDetail).is().visible();
        PhotoAlbumDTO actual_photo = new PhotoAlbumDTO();
        actual_photo.setName(nameDetail.getText());
        Assert.assertEquals(expected_photo.getName(),actual_photo.getName());
       
          
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void deletePhoto() throws InterruptedException {
       login();
       Logger.getAnonymousLogger().info("waiting");
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
      
        WebElement detailBtn = driver.findElement(By.id("0-detail-btn"));
        waitGui().until().element(detailBtn).is().visible();
        detailBtn.click();
        WebElement photoList = driver.findElement(By.id("photoAlbum-bicycle")); 
        waitGui().until().element(photoList).is().visible();
        photoList.click();
      driver.get(deploymentURL.toExternalForm()+"#/bicycles/1/details/photoAlbum/1/delete");
        WebElement confirmDel = driver.findElement(By.id("confirm-delete"));
       waitGui().until().element(confirmDel).is().visible();
        confirmDel.click();
        Integer expected = 0;
        Integer countPhotos = driver.findElements(By.cssSelector("tbody > tr")).size();
       deleteBicycle();
        Assert.assertEquals(expected, countPhotos);
    }
 
}