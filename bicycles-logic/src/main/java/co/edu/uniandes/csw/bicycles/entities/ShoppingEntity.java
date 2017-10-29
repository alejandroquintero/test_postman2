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
import co.edu.uniandes.csw.crud.spi.entity.PaymentStatus;
import java.security.Timestamp;
import java.util.ArrayList;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

/**
 * Entidad de compras (orden de compra).
 * @author cc.huertas
 */
@Entity
public class ShoppingEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private ClientEntity client;
    
    @PodamExclude
    @OneToMany(mappedBy = "shopping", cascade = CascadeType.REMOVE)
    private List<ItemShoppingEntity> itemShopping = new ArrayList<>();
    
    @Column(name = "paymentStatus")
    @Enumerated(EnumType.STRING)
    private String status;
    
    private Timestamp dateOfPurchase;
    private Double totalPrice;

    /**
     * Obtener cliente
     * @return cliente 
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Camabiar cliente
     * @param client cliente
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Obtener la lista de objetos de la orden de compra
     * @return ItemShopping 
     */
    public List<ItemShoppingEntity> getItemShopping() {
        return itemShopping;
    }

    /**
     * Camabiar lista de Items
     * @param itemShopping
     */
    public void setItemShopping(List<ItemShoppingEntity> itemShopping) {
        this.itemShopping = itemShopping;
    }

    /**
     * Obtener el estado de la orden de compra.
     * @return estado.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Cambiar estado de la orden
     * @param status estado de la orden
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtener la fecha de la compra
     * @return fecha de compra.
     */
    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     * Cambiar la fecha de la compra
     * @param dateOfPurchase fecha de compra
     */
    public void setDateOfPurchase(Timestamp dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     * Obtener el total de la compra
     * @return precio total.
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Cambiar el total de la compra
     * @param totalPrice precio total
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
