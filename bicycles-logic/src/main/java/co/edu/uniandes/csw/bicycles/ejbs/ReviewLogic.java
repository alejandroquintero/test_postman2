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
package co.edu.uniandes.csw.bicycles.ejbs;

import co.edu.uniandes.csw.bicycles.api.IBicycleLogic;
import co.edu.uniandes.csw.bicycles.api.IReviewLogic;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ReviewEntity;
import co.edu.uniandes.csw.bicycles.persistence.ReviewPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ReviewLogic implements IReviewLogic {

    @Inject private ReviewPersistence persistence;
    //@Inject private IShoppingLogic shoppingLogic;
    @Inject private IBicycleLogic bicycleLogic;
    
    /**
     * Obtiene el número de registros de Review.
     *
     * @return Número de registros de Review.
     * @generated
     */
    @Override
    public int countReviews() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Review que pertenecen a un Bicycle.
     *
     * @param bicycleid id del Bicycle el cual es padre de los Reviews.
     * @return Colección de objetos de ReviewEntity.
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Long bicycleid) {
        BicycleEntity bicycle = bicycleLogic.getBicycle(bicycleid);
        return bicycle.getReview();
    }

    /**
     * Obtiene la lista de los registros de Review que pertenecen a un Bicycle indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param bicycleid id del Bicycle el cual es padre de los Reviews.
     * @return Colección de objetos de ReviewEntity.
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Integer page, Integer maxRecords, Long bicycleid) {
        return persistence.findAll(page, maxRecords, bicycleid);
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID.
     *
     * @pre La existencia del elemento padre Bicycle se debe garantizar.
     * @param reviewId Identificador del Review a consultar
     * @return Instancia de ReviewEntity con los datos del Review consultado.
     * @generated
     */
    @Override
    public ReviewEntity getReview(Long reviewId) {
        try {
            return persistence.find(reviewId);
        }catch(NoResultException e){
            Logger.getAnonymousLogger().info(e.getMessage());
            throw new IllegalArgumentException("El Review no existe");
        }
    }

    /**
     * Se encarga de crear un Review en la base de datos.
     *
     * @param entity Objeto de ReviewEntity con los datos nuevos
     * @param bicycleid id del Bicycle el cual sera padre del nuevo Review.
     * @return Objeto de ReviewEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public ReviewEntity createReview(Long bicycleid, ReviewEntity entity) {
        BicycleEntity bicycle = bicycleLogic.getBicycle(bicycleid);
        entity.setBicycle(bicycle);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param entity Instancia de ReviewEntity con los nuevos datos.
     * @param bicycleid id del Bicycle el cual sera padre del Review actualizado.
     * @return Instancia de ReviewEntity con los datos actualizados.
     * @generated
     */
    @Override
    public ReviewEntity updateReview(Long bicycleid, ReviewEntity entity) {
        BicycleEntity bicycle = bicycleLogic.getBicycle(bicycleid);
        entity.setBicycle(bicycle);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteReview(Long id) {
        ReviewEntity old = getReview(id);
        persistence.delete(old.getId());
    }
}
