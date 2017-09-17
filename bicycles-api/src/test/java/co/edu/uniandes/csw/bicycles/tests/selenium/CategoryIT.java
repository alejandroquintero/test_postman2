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

import co.edu.uniandes.csw.bicycles.dtos.minimum.CategoryDTO;
import co.edu.uniandes.csw.bicycles.resources.CategoryResource;
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
public class CategoryIT {
    
    
     @ArquillianResource
    private URL deploymentURL;

 @Drone
  private WebDriver driver;
 
 private static PodamFactory factory = new PodamFactoryImpl();

 
 
 private static  Properties prop;
    private static InputStream input = null;
    private static final String path = System.getenv("AUTH0_PROPERTIES");

static {
            prop = new Properties();
        try {
            input =  new FileInputStream(path);
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
                .addPackage(CategoryResource.class.getPackage())
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
public void setup() throws InterruptedException{
try {
             driver = new RemoteWebDriver(
                     new URL("http://localhost:4445/wd/hub"), DesiredCapabilities.chrome()
             );
         } catch (MalformedURLException ex) {
             Logger.getLogger(BicycleIT.class.getName()).log(Level.SEVERE, null, ex);
         }  
    login();
}
@After
public void unload(){

driver.quit();
}

public  void login() throws InterruptedException{
    
        driver.manage().window().maximize(); 
        driver.get(deploymentURL.toExternalForm()+"#/login");   
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


    @Test
    @InSequence(0)
    @RunAsClient
    public void createCategory() throws InterruptedException  {
        Logger.getAnonymousLogger().info("waiting for /bicycles/list");
      while(!"/bicycles/list".equals(driver.getCurrentUrl().split("#")[1])){
      
      }
     driver.get(deploymentURL.toExternalForm()+"#/categorys/list");
       Logger.getAnonymousLogger().info("waiting");
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
       Integer expected = 0;
       Integer countCategorys = driver.findElements(By.cssSelector("tbody > tr")).size();
       Assert.assertEquals(expected,countCategorys);
       WebElement createBtn = driver.findElement(By.id("create-category"));
       waitModel().until().element(createBtn).is().visible();
       createBtn.click();
       CategoryDTO expected_category = factory.manufacturePojo(CategoryDTO.class);
       WebElement nameInput = driver.findElement(By.id("name"));
       WebElement descriptionInput = driver.findElement(By.id("description"));
       WebElement modalityInput = driver.findElement(By.id("modality"));
       WebElement weightInput = driver.findElement(By.id("weight"));
       WebElement saveBtn = driver.findElement(By.id("save-category"));
       descriptionInput.clear();
       descriptionInput.sendKeys(expected_category.getDescription());
       modalityInput.clear();
       modalityInput.sendKeys(expected_category.getModality());
       weightInput.clear();
       weightInput.sendKeys(expected_category.getWeight());
       nameInput.clear();
       nameInput.sendKeys(expected_category.getName()); 
       saveBtn.click();
       WebElement  nameDetail = driver.findElement(By.id("name-detail")); 
       waitGui().until().element(nameDetail).is().visible();     
       CategoryDTO actual_category = new CategoryDTO();
       actual_category.setName(nameDetail.getText());
       Assert.assertEquals(expected_category.getName(), actual_category.getName()); 
    }
    

    @Test
   @InSequence(1)
    @RunAsClient
    public void editCategory() throws InterruptedException {
        
      Logger.getAnonymousLogger().info("waiting for /bicycles/list");
      while(!"/bicycles/list".equals(driver.getCurrentUrl().split("#")[1])){
      
      }
     driver.get(deploymentURL.toExternalForm()+"#/categorys/list");
       
       Logger.getAnonymousLogger().info("waiting");
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
        CategoryDTO expected_category = factory.manufacturePojo(CategoryDTO.class);
        WebElement editBtn = driver.findElement(By.id("0-edit-btn"));
        waitGui().until().element(editBtn).is().visible();
        editBtn.click();
       WebElement nameInput = driver.findElement(By.id("name"));
       WebElement descriptionInput = driver.findElement(By.id("description"));
       WebElement modalityInput = driver.findElement(By.id("modality"));
       WebElement weightInput = driver.findElement(By.id("weight"));
       nameInput.clear();
       descriptionInput.clear();
       modalityInput.clear();
       weightInput.clear();
       nameInput.sendKeys(expected_category.getName());
       descriptionInput.sendKeys(expected_category.getDescription());
       modalityInput.sendKeys(expected_category.getModality());
       weightInput.sendKeys(expected_category.getWeight());
       WebElement saveBtn = driver.findElement(By.id("save-category"));
       saveBtn.click();
         WebElement  nameDetail = driver.findElement(By.id("name-detail")); 
       waitGui().until().element(nameDetail).is().visible(); 
        CategoryDTO actual_category = new CategoryDTO();
       actual_category.setName(nameDetail.getText());
       Assert.assertEquals(expected_category.getName(), actual_category.getName());
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void deleteCategory() throws InterruptedException {
         Logger.getAnonymousLogger().info("waiting for /bicycles/list");
      while(!"/bicycles/list".equals(driver.getCurrentUrl().split("#")[1])){
      
      }
     driver.get(deploymentURL.toExternalForm()+"#/categorys/list");
   
       Logger.getAnonymousLogger().info("waiting");
       driver.manage().timeouts().implicitlyWait(5, SECONDS);
       WebElement deleteBtn = driver.findElement(By.id("0-delete-btn"));
        waitGui().until().element(deleteBtn).is().visible();
        deleteBtn.click();
        WebElement confirmDel = driver.findElement(By.id("confirm-delete"));
       waitGui().until().element(confirmDel).is().visible();
        confirmDel.click();
        Integer expected = 0;
        Integer countCategorys = driver.findElements(By.cssSelector("tbody > tr")).size();
        Assert.assertEquals(expected, countCategorys);
    }
}