package co.edu.uniandes.csw.bicycles.test.persistence;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import co.edu.uniandes.csw.bicycles.persistence.ShoppingPersistence;
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
public class ShoppingPersistenceTest {

    /**
     * @return 
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ShoppingEntity.class.getPackage())
                .addPackage(ShoppingPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    ClientEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private ShoppingPersistence ShoppingPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
     * @generated
     */
    private List<ShoppingEntity> data = new ArrayList<ShoppingEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
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
     * Prueba para crear un Shopping.
     *
     * @generated
     */
    @Test
    public void createShoppingTest() {
	PodamFactory factory = new PodamFactoryImpl();
        ShoppingEntity newEntity = factory.manufacturePojo(ShoppingEntity.class);
        newEntity.setClient(fatherEntity);
        ShoppingEntity result = ShoppingPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ShoppingEntity entity = em.find(ShoppingEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Shoppings.
     *
     * @generated
     */
    @Test
    public void getShoppingTest() {
        List<ShoppingEntity> list = ShoppingPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (ShoppingEntity ent : list) {
            boolean found = false;
            for (ShoppingEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Shopping.
     *
     * @generated
     */
    @Test
    public void getShoppingOneTest() {
        ShoppingEntity entity = data.get(0);
        ShoppingEntity newEntity = ShoppingPersistence.find(entity.getClient().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Shopping.
     *
     * @generated
     */
    @Test
    public void deleteShoppingTest() {
        ShoppingEntity entity = data.get(0);
        ShoppingPersistence.delete(entity.getId());
        ShoppingEntity deleted = em.find(ShoppingEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Shopping.
     *
     * @generated
     */
    @Test
    public void updateShoppingTest() {
        ShoppingEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ShoppingEntity newEntity = factory.manufacturePojo(ShoppingEntity.class);

        newEntity.setId(entity.getId());

        ShoppingPersistence.update(newEntity);

        ShoppingEntity resp = em.find(ShoppingEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
