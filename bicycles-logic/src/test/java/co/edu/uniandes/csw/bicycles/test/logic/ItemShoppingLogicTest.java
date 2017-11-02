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
package co.edu.uniandes.csw.bicycles.test.logic;

import co.edu.uniandes.csw.bicycles.ejbs.BicycleLogic;
import co.edu.uniandes.csw.bicycles.api.IBicycleLogic;
import co.edu.uniandes.csw.bicycles.api.IItemShoppingLogic;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.persistence.BicyclePersistence;
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
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
public class ItemShoppingLogicTest {

    /**
     * @generated
     */
    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IItemShoppingLogic itemShoppingLogic;
    
    @Inject
    private IItemShoppingLogic bicycleLogic;

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
    private List<ItemShoppingEntity> data = new ArrayList<ItemShoppingEntity>();
    
    private List<BicycleEntity> dataBicycle = new ArrayList<BicycleEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BicycleEntity.class.getPackage())
                .addPackage(BicycleLogic.class.getPackage())
                .addPackage(IBicycleLogic.class.getPackage())
                .addPackage(BicyclePersistence.class.getPackage())
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
        em.createQuery("delete from ItemShoppingEntity").executeUpdate();
        em.createQuery("delete from BicycleEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ItemShoppingEntity brand = factory.manufacturePojo(ItemShoppingEntity.class);
            em.persist(brand);
            data.add(brand);
        }

        for (int i = 0; i < 3; i++) {
            BicycleEntity entity = factory.manufacturePojo(BicycleEntity.class);
            em.persist(entity);
            data.get(i).setBicycle(entity);  
        }
    }

    /**
     * Prueba para crear un Bicycle
     *
     * @generated
     */
    @Test
    public void createItemTest() {
        ItemShoppingEntity newEntity = factory.manufacturePojo(ItemShoppingEntity.class);
        ItemShoppingEntity result = itemShoppingLogic.createItemShopping(newEntity);
        Assert.assertNotNull(result);
        ItemShoppingEntity entity = em.find(ItemShoppingEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Bicycles
     *
     * @generated
     */
    @Test
    public void getItemTest() {
        List<ItemShoppingEntity> list = itemShoppingLogic.getItemShoppingList();
        Assert.assertEquals(data.size(), list.size());
        for (ItemShoppingEntity entity : list) {
            boolean found = false;
            for (ItemShoppingEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Bicycle
     *
     * @generated
     */
    @Test
    public void getItemsTest() {
        ItemShoppingEntity entity = data.get(0);
        ItemShoppingEntity resultEntity = itemShoppingLogic.getItemShopping(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Bicycle
     *
     * @generated
     */
    @Test
    public void deleteItemTest() {
        ItemShoppingEntity entity = data.get(0);
        itemShoppingLogic.deleteItemShopping(entity.getId());
        ItemShoppingEntity deleted = em.find(ItemShoppingEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Bicycle
     *
     * @generated
     */
    @Test
    public void updateBicycleTest() {
        ItemShoppingEntity entity = data.get(0);
        ItemShoppingEntity pojoEntity = factory.manufacturePojo(ItemShoppingEntity.class);

        pojoEntity.setId(entity.getId());

        itemShoppingLogic.updateItemShopping(pojoEntity);

        ItemShoppingEntity resp = em.find(ItemShoppingEntity.class, entity.getId());

        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}
