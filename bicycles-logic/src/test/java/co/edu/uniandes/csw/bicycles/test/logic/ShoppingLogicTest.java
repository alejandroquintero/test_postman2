package co.edu.uniandes.csw.bicycles.test.logic;

import co.edu.uniandes.csw.bicycles.ejbs.ShoppingLogic;
import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import co.edu.uniandes.csw.bicycles.persistence.ShoppingPersistence;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ShoppingLogicTest {

    /**
     * @generated
     */
    ClientEntity fatherEntity;

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IShoppingLogic shoppingLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<ShoppingEntity> data = new ArrayList<ShoppingEntity>();
    /**
     * @generated
     */
    private List<ClientEntity> clientData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ShoppingEntity.class.getPackage())
                .addPackage(ShoppingLogic.class.getPackage())
                .addPackage(IShoppingLogic.class.getPackage())
                .addPackage(ShoppingPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
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
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ShoppingEntity").executeUpdate();
        em.createQuery("delete from ClientEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
    
            fatherEntity = factory.manufacturePojo(ClientEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            ShoppingEntity entity = factory.manufacturePojo(ShoppingEntity.class);
                entity.setClient(fatherEntity);
    

            em.persist(entity);
            data.add(entity);
        }
    }
   /**
     * Prueba para crear un Shopping
     *
     * @generated
     */
    @Test
    public void createShoppingTest() {
        ShoppingEntity newEntity = factory.manufacturePojo(ShoppingEntity.class);
        ShoppingEntity result = shoppingLogic.createShopping(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        ShoppingEntity entity = em.find(ShoppingEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Shoppings
     *
     * @generated
     */
    @Test
    public void getShoppingTest() {
        List<ShoppingEntity> list = shoppingLogic.getShoppingList(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ShoppingEntity entity : list) {
            boolean found = false;
            for (ShoppingEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Shopping
     *
     * @generated
     */
    @Test
    public void getShoppingOneTest() {
        ShoppingEntity entity = data.get(0);
        ShoppingEntity resultEntity = shoppingLogic.getShopping(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Shopping
     * No implementadas
     * @generated
     */
    /*@Test
    public void deleteShoppingTest() {
        ShoppingEntity entity = data.get(0);
        shoppingLogic.deleteShopping(entity.getId());
        ShoppingEntity deleted = em.find(ShoppingEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }*/

    /**
     * Prueba para actualizar un Shopping
     * No implementadas
     * @generated
     */
    /*@Test
    public void updateShoppingTest() {
        ShoppingEntity entity = data.get(0);
        ShoppingEntity pojoEntity = factory.manufacturePojo(ShoppingEntity.class);

        pojoEntity.setId(entity.getId());

        shoppingLogic.updateShopping(fatherEntity.getId(), pojoEntity);

        ShoppingEntity resp = em.find(ShoppingEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }*/
}

