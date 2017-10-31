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

import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
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

    @Inject private IClientLogic clientLogic;
    
    /**
     * Trae las compras paginado.
     * @param page
     * @param maxRecords
     * @param clientId
     * @return 
     */    
    @Override
    public List<ShoppingEntity> getShopping(Integer page, Integer maxRecords, Long clientId) {
        return persistence.findAll(page, maxRecords, clientId);
    }

    /**
     * Trae las compras paginado.
     * @param clientId
     * @return 
     */    
    @Override
    public List<ShoppingEntity> getShoppingList(Long clientId) {
        ClientEntity client = clientLogic.getClient(clientId);
        return client.getShopping();
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

    /**
     * Contar las compras.
     * @return numero de compras.
     */
    @Override
    public int countShopping() {
        return persistence.count();
    }

    /**
     * Crear compra.
     * @param clientId Id Cliente.
     * @param entity Entidad Compra.
     * @return Retorna la compra.
     */
    @Override
    public ShoppingEntity createShopping(Long clientId, ShoppingEntity entity) {
        ClientEntity client = clientLogic.getClient(clientId);
        entity.setClient(client);
        entity.setStatus("PROCESO");
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualizar.
     * @param shoppingId
     * @param entity
     * @return 
     */
    @Override
    public ShoppingEntity updateShopping(Long shoppingId, ShoppingEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Borrar compra.
     * @param id ID compra.
     */
    @Override
    public void deleteShopping(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Devuelve la compra en estado Pendiente.
     * @param clientID id del cliente.
     * @return ShoppingEntity
     */
    @Override
    public ShoppingEntity getShoppingCar(Long clientID) {
        return persistence.getShoppingCar(clientID);
    }
    
    /**
     * Realizar el pago de la compra pendiente.
     * @param clientID id del cliente.
     */
    @Override
    public void checkoutShoppingCar(Long clientID) {
        ShoppingEntity shopping = persistence.getShoppingCar(clientID);
        persistence.checkoutShopping(shopping.getId());        
    }
}
