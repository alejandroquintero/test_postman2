package co.edu.uniandes.csw.bicycles.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.bicycles.entities.ReviewEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * @generated
 */
@Stateless
public class ReviewPersistence extends CrudPersistence<ReviewEntity> {

    @PersistenceContext(unitName="BicyclesPU")
    protected EntityManager em;

    /**
     * Manejador.
     * @return 
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @return 
     * @generated
     */
    @Override
    protected Class<ReviewEntity> getEntityClass() {
        return ReviewEntity.class;
    }

    public ReviewEntity find(Long bicycleid, Long reviewId) {
        TypedQuery<ReviewEntity> q = em.createQuery("select p from ReviewEntity p where (p.bicycle.id = :bicycleid) and (p.id = :reviewId)", ReviewEntity.class);
        q.setParameter("bicycleid", bicycleid);
        q.setParameter("reviewId", reviewId);
        return q.getSingleResult();
    }
    
    public List<ReviewEntity> findAll(Integer page, Integer maxRecords, Long bicycleid) {
        TypedQuery<ReviewEntity> q = em.createQuery("select p from ReviewEntity p where (p.bicycle.id = :bicycleid)", ReviewEntity.class);
        q.setParameter("bicycleid", bicycleid);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
}
