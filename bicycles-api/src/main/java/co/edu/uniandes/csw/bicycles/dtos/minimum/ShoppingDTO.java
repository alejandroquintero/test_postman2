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
package co.edu.uniandes.csw.bicycles.dtos.minimum;

import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import co.edu.uniandes.csw.crud.spi.entity.PaymentStatus;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 * @generated
 */
@XmlRootElement
public class ShoppingDTO implements Serializable {

    private String status;
    private Long id;
    private Timestamp dateOfPurchase;
    private Double totalPrice;
    
    /**
     * @generated
     */
    public ShoppingDTO() {
    }

    /**
     * Crea un objeto BicycleDTO a partir de un objeto BicycleEntity.
     *
     * @param entity Entidad BicycleEntity desde la cual se va a crear el nuevo
     * objeto.
     * @generated
     */
    public ShoppingDTO(ShoppingEntity entity) {
        if (entity!=null)
        {
            this.status = entity.getStatus();
            this.id = entity.getId();
            this.dateOfPurchase = entity.getDateOfPurchase();
            this.totalPrice = entity.getTotalPrice();
        }
    }

    /**
     * Convierte un objeto BicycleDTO a BicycleEntity.
     *
     * @return Nueva objeto BicycleEntity.
     * @generated
     */
    public ShoppingEntity toEntity() {
        ShoppingEntity entity = new ShoppingEntity();
        entity.setId(this.getId());
        entity.setStatus(this.getStatus());
        entity.setDateOfPurchase(this.getDateOfPurchase());
        return entity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Timestamp dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    } 
}
