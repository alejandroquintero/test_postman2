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
package co.edu.uniandes.csw.bicycles.test.persistence;
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
public class FavoritePersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FavoriteEntity.class.getPackage())
                .addPackage(FavoritePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */

    /**
     * @generated
     */
    @Inject
    private FavoritePersistence favoritePersistence;

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
        em.createQuery("delete from FavoriteEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<FavoriteEntity> data = new ArrayList<FavoriteEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FavoriteEntity entity = factory.manufacturePojo(FavoriteEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Favorito.
     *
     * @generated
     */
    @Test
    public void createFavoriteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FavoriteEntity newEntity = factory.manufacturePojo(FavoriteEntity.class);
        FavoriteEntity result = favoritePersistence.create(newEntity);

        Assert.assertNotNull(result);

        FavoriteEntity entity = em.find(FavoriteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getBicycle(), entity.getBicycle());
        Assert.assertEquals(newEntity.getClient(), entity.getClient());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Favoritos
     *
     * @generated
     */
    @Test
    public void getFavoritesTest() {
        List<FavoriteEntity> list = favoritePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FavoriteEntity ent : list) {
            boolean found = false;
            for (FavoriteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Category.
     *
     * @generated
     */
    @Test
    public void getFavoriteTest() {
        FavoriteEntity entity = data.get(0);
        FavoriteEntity newEntity = favoritePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getBicycle(), newEntity.getBicycle());
        Assert.assertEquals(entity.getClient(), newEntity.getClient());
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Favorite.
     *
     * @generated
     */
    @Test
    public void deleteFavoriteTest() {
        FavoriteEntity entity = data.get(0);
        favoritePersistence.delete(entity.getId());
        FavoriteEntity deleted = em.find(FavoriteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Category.
     *
     * @generated
     */
    @Test
    public void updateFavoriteTest() {
        FavoriteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FavoriteEntity newEntity = factory.manufacturePojo(FavoriteEntity.class);

        newEntity.setId(entity.getId());

        favoritePersistence.update(newEntity);

        FavoriteEntity resp = em.find(FavoriteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getBicycle(), resp.getBicycle());
        Assert.assertEquals(newEntity.getClient(), resp.getClient());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
