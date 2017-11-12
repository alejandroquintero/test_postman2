package co.edu.uniandes.csw.bicycles.tests.rest;

import co.edu.uniandes.csw.auth.conexions.AuthenticationApi;
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.dtos.detail.ClientDetailDTO;
import co.edu.uniandes.csw.bicycles.resources.ClientResource;
import co.edu.uniandes.csw.bicycles.tests.Utils;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: clients/{reviewId: \\d+}/review/
 */
@RunWith(Arquillian.class)
public class ClientTest {

    private WebTarget target;
    private final String apiPath = Utils.apiPath;
    private final String username = Utils.username;
    private final String password = Utils.password;
    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<ClientEntity> oraculo = new ArrayList<>();
    private AuthenticationApi auth;

    private final String clientPath = "clients";
       

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ClientResource.class.getPackage())
                .addPackage("co.edu.uniandes.csw.auth.properties")
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "BicyclesPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        em.createQuery("delete from ClientEntity").executeUpdate();
        oraculo.clear();
    }
/**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        for (int i = 0; i < 3; i++) {            
            ClientEntity client = factory.manufacturePojo(ClientEntity.class);
            client.setId(i + 1L);
            em.persist(client);
            oraculo.add(client);
        }
    }
    
    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void setUpTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        target = createWebTarget()
                .path(clientPath);
    }

    /**
     * Login para poder consultar los diferentes servicios
     *
     * @throws java.io.IOException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     * @throws org.json.JSONException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @return Cookie con información de la sesión del usuario
     * @generated
     */
    public String login() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        auth = new AuthenticationApi();
        UserDTO user = new UserDTO();
        user.setUserName(auth.getProp().getProperty("username").trim());
        user.setPassword(auth.getProp().getProperty("password").trim());
        JSONObject json = new JSONObject(auth.authenticationToken(user).getBody());
        return (String) json.get("id_token");
    }

    public String getUsername() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        auth = new AuthenticationApi();
        return auth.getProp().getProperty("username").trim();
    }

    /**
     * Prueba para crear un Client
     *
     * @throws java.io.IOException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     * @throws org.json.JSONException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    @Test
    public void createClientTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        ClientDetailDTO review = factory.manufacturePojo(ClientDetailDTO.class);
        String token = login();

        Response response = target
                .request()
                .cookie("username", getUsername())
                .cookie("id_token", token)
                .post(Entity.entity(review, MediaType.APPLICATION_JSON));

        ClientDetailDTO reviewTest = (ClientDetailDTO) response.readEntity(ClientDetailDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(review.getName(), reviewTest.getName());
        Assert.assertEquals(review.getEmail(), reviewTest.getEmail());

        ClientEntity entity = em.find(ClientEntity.class, reviewTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Client
     *
     * @throws java.io.IOException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     * @throws org.json.JSONException
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     * @generated
     */
    @Test
    public void getClientByIdTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token = login();

        ClientDetailDTO reviewTest = target
                .path(oraculo.get(0).getId().toString())
                .request()
                .cookie("username", getUsername())
                .cookie("id_token", token)
                .get(ClientDetailDTO.class);

        Assert.assertEquals(reviewTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(reviewTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(reviewTest.getEmail(), oraculo.get(0).getEmail());
    }

    /**
     * Prueba para consultar la lista de Clients
     *
     * @throws java.io.IOException
     * @throws java.util.concurrent.ExecutionException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     * @throws java.lang.InterruptedException
     * @throws org.json.JSONException
     * @generated
     */
    @Test
    public void listClientTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token = login();

        Response response = target
                .request()
                .cookie("username", getUsername())
                .cookie("id_token", token)
                .get();

        String listClient = response.readEntity(String.class);
        List<ClientDetailDTO> listClientTest = new ObjectMapper().readValue(listClient, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(oraculo.size(), listClientTest.size());
    }

    /**
     * Prueba para actualizar un Client
     * @throws java.io.IOException
     * @throws java.util.concurrent.ExecutionException
     * @throws com.mashape.unirest.http.exceptions.UnirestException
     * @throws java.lang.InterruptedException
     * @throws org.json.JSONException
     * @generated
     */
    @Test
    public void updateClientTest() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        String token = login();
        ClientDetailDTO review = new ClientDetailDTO(oraculo.get(0));

        ClientDetailDTO reviewChanged = factory.manufacturePojo(ClientDetailDTO.class);

        review.setName(reviewChanged.getName());
        review.setEmail(reviewChanged.getEmail());

        Response response = target
                .path(review.getId().toString())
                .request()
                .cookie("username", getUsername())
                .cookie("id_token", token)
                .put(Entity.entity(review, MediaType.APPLICATION_JSON));

        ClientDetailDTO reviewTest = (ClientDetailDTO) response.readEntity(ClientDetailDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(review.getName(), reviewTest.getName());
        Assert.assertEquals(review.getEmail(), reviewTest.getEmail());
    }
}
