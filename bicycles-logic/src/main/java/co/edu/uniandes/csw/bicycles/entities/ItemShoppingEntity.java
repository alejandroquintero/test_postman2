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
package co.edu.uniandes.csw.bicycles.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Entidad de item de compra.
 * @author cc.huerats
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "itemShopping.getByIdShopping", query = "select u from ItemShoppingEntity u Where u.shopping.id = :shoppingId")
})
public class ItemShoppingEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private ShoppingEntity shopping;
    
    @PodamExclude
    @OneToOne
    private BicycleEntity bicycle;
        
    private Long quantity;
    
    private String tempUser;

    /**
     * Obtener orden de compra
     * @return 
     */
    public ShoppingEntity getShopping() {
        return shopping;
    }
    
    /**
     * Cambia la orden de compra
     * @param shopping 
     */
    public void setShopping(ShoppingEntity shopping) {
        this.shopping = shopping;
    }

    /**
     * Obtener la bicicleta
     * @return bicicleta
     */
    public BicycleEntity getBicycle() {
        return bicycle;
    }

    /**
     * Cambibar la bicicleta
     * @param bicycle 
     */
    public void setBicycle(BicycleEntity bicycle) {
        this.bicycle = bicycle;
    }

    /**
     * Obtener la cantidad de bicilcetas
     * @return cantidad
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Cambiar la cantidad de bicicletas
     * @param quantity 
     */
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }   

    public String getTempUser() {
        return tempUser;
    }

    public void setTempUser(String tempUser) {
        this.tempUser = tempUser;
    }
    
    
}