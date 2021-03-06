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
package co.edu.uniandes.csw.bicycles.persistence;

import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;

/**
 * @generated
 */
@Stateless
public class ItemShoppingPersistence extends CrudPersistence<ItemShoppingEntity> {

    @PersistenceContext(unitName="BicyclesPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<ItemShoppingEntity> getEntityClass() {
        return ItemShoppingEntity.class;
    }

    /**
     * Find all 
     * @param page
     * @param maxRecords
     * @param shoppingId
     * @return 
     */
    public List<ItemShoppingEntity> findAll(Integer page, Integer maxRecords, Long shoppingId) {
        TypedQuery<ItemShoppingEntity> q = em.createQuery("select * from ItemShoppingEntity where (p.shopping.id = :shoppingId)", ItemShoppingEntity.class);
        q.setParameter("shoppingId", shoppingId);
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return q.getResultList();
    }
    
    /**
     * Find all 
     * @param shoppingId
     * @return 
     */
    public List<ItemShoppingEntity> findAll( Long shoppingId) {
        Map<String, Object> params = new HashMap<>();
        params.put("shoppingId", shoppingId);
        return executeListNamedQuery("itemShopping.getByIdShopping", params);
    }
}
