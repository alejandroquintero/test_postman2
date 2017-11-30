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

import co.edu.uniandes.csw.bicycles.api.IFavoriteLogic;
import co.edu.uniandes.csw.bicycles.ejbs.FavoriteLogic;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;
import co.edu.uniandes.csw.bicycles.persistence.FavoritePersistence;
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
public class FavoriteLogicTest {

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
    private IFavoriteLogic favoriteLogic;

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
    private List<FavoriteEntity> data = new ArrayList<FavoriteEntity>();
    /**
     * @generated
     */
    private List<ClientEntity> clientData = new ArrayList<>();
    /**
     * @generated
     */
    private List<BicycleEntity> bicycleData = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FavoriteEntity.class.getPackage())
                .addPackage(FavoriteLogic.class.getPackage())
                .addPackage(IFavoriteLogic.class.getPackage())
                .addPackage(FavoritePersistence.class.getPackage())
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
        em.createQuery("delete from ClientEntity").executeUpdate();
        em.createQuery("delete from BicycleEntity").executeUpdate();
        em.createQuery("delete from FavoriteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ClientEntity client = factory.manufacturePojo(ClientEntity.class);
            em.persist(client);
            clientData.add(client);
        }
        for (int i = 0; i < 3; i++) {
            BicycleEntity bicycleEntity = factory.manufacturePojo(BicycleEntity.class);
            em.persist(bicycleEntity);
            bicycleData.add(bicycleEntity);
        }
        for (int i = 0; i < 3; i++) {
            FavoriteEntity entity = factory.manufacturePojo(FavoriteEntity.class);
            entity.setClient(clientData.get(i));
            entity.setBicycle(bicycleData.get(i));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Bicycle
     *
     * @generated
     */
    @Test
    public void createBicycleTest() {
        FavoriteEntity newEntity = factory.manufacturePojo(FavoriteEntity.class);
        BicycleEntity be = factory.manufacturePojo(BicycleEntity.class);
        ClientEntity ce = factory.manufacturePojo(ClientEntity.class);
        newEntity.setBicycle(be);
        newEntity.setClient(ce);
        
        FavoriteEntity result = favoriteLogic.createFavorite(newEntity.getBicycle().getId(), newEntity.getClient().getLogin());
        Assert.assertNotNull(result);
    }

    
    /**
     * Prueba para eliminar un Bicycle
     *
     * @generated
     */
    @Test
    public void deleteBicycleTest() {
        FavoriteEntity entity = data.get(0);
        favoriteLogic.deleteFavorite(entity.getBicycle().getId(), entity.getClient().getLogin());
        FavoriteEntity deleted = em.find(FavoriteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
