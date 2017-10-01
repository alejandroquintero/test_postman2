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
import java.util.ArrayList;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad de compras.
 * @author ra.gomez11
 */
@Entity
public class ShoppingEntity extends BaseEntity implements Serializable {

    @Column(name = "paymentStatus")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @PodamExclude
    @OneToMany(mappedBy = "shopping")
    private List<BicycleEntity> bicycle = new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
    private ClientEntity client;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateOfPurchase")
    private java.util.Date dateOfPurchase;

    /**
     * Obtener el estado del pago.
     * @return estado.
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Cambiar estado del pago
     * @param paymentStatus estado del pago
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Obtener cliente
     * @return 
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
     * Obtener fecha de compra
     * @return fecha de compra
     */
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     * Cambiar fecha de compra.
     * @param dateOfPurchase 
     */
    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     * Obtener bicicletas.
     * @return bicicletas.
     */
    public List<BicycleEntity> getBicycle() {
        return bicycle;
    }

    /**
     * Camabiar bicicletas
     * @param bicycle bicicleta
     */
    public void setBicycle(List<BicycleEntity> bicycle) {
        this.bicycle = bicycle;
    }

}
