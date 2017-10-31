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
import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.api.IItemShoppingLogic;
import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import co.edu.uniandes.csw.bicycles.persistence.ItemShoppingPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ItemShoppingLogic implements IItemShoppingLogic {

    @Inject private ItemShoppingPersistence persistence;
    @Inject private IShoppingLogic shoppingLogic;
    @Inject private IBicycleLogic bicycleLogic;
    
    /**
     * Trae los items de las compras paginado.
     * @param page
     * @param maxRecords
     * @param shoppingId
     * @return 
     */    
    @Override
    public List<ItemShoppingEntity> getItemShopping(Integer page, Integer maxRecords, Long shoppingId) {
        return persistence.findAll(page, maxRecords, shoppingId);
    }

    /**
     * Trae los items de una compra.
     * @param shoppingId
     * @return 
     */    
    @Override
    public List<ItemShoppingEntity> getItemShoppingList(Long shoppingId) {
        return persistence.findAll(shoppingId);
    }
    
    /**
     * Un solo item de compra.
     * @param itemShoppingId
     * @return 
     */
    @Override
    public ItemShoppingEntity getItemShopping(Long itemShoppingId) {
        return persistence.find(itemShoppingId);
    }

    /**
     * Contar las compras.
     * @param shoppingId
     * @return numero de compras.
     */
    @Override
    public int countItemShopping(Long shoppingId) {
        return persistence.findAll(shoppingId).size();
    }

    /**
     * Borrar item compra.
     * @param itemShoppingId ID item compra.
     */
    @Override
    public void deleteItemShopping(Long itemShoppingId) {
        persistence.delete(itemShoppingId);
    }

    @Override
    public ItemShoppingEntity addItemShopping(Long clientId, Long quantity, Long bicycleId) {
        ItemShoppingEntity itemShoppingEntity = new ItemShoppingEntity();
        
        ShoppingEntity shopping = shoppingLogic.getShoppingCar(clientId);
        BicycleEntity bicycle = bicycleLogic.getBicycle(bicycleId);
        
        if(shopping == null){
            shopping = new ShoppingEntity();
            shopping.setTotalPrice(new Double(0));
            shopping = shoppingLogic.createShopping(clientId, shopping);
        }
        
        itemShoppingEntity.setBicycle(bicycle);
        itemShoppingEntity.setQuantity(quantity);
        itemShoppingEntity.setShopping(shopping);
        
        return persistence.create(itemShoppingEntity);
    }
}