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

import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import co.edu.uniandes.csw.bicycles.persistence.ShoppingPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ShoppingLogic implements IShoppingLogic {

    @Inject private ShoppingPersistence persistence;

    /**
     * Trae todas las compras.
     * @return 
     */
    @Override
    public List<ShoppingEntity> getShopping() {
        return persistence.findAll();
    }
    
    /**
     * Trae las compras paginado.
     * @param page
     * @param maxRecords
     * @return 
     */    
    @Override
    public List<ShoppingEntity> getShopping(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Una sola compra.
     * @param id
     * @return 
     */
    @Override
    public ShoppingEntity getShopping(Long id) {
        return persistence.find(id);
    }

    @Override
    public int countShopping() {
        return persistence.count();
    }

    @Override
    public ShoppingEntity createShopping(ShoppingEntity entity) {
      entity.setDateOfPurchase(new java.util.Date());
        persistence.create(entity);
        return entity;  
        
    }
}
