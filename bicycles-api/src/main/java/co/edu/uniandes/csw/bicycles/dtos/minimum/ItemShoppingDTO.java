/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.minimum;


import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import co.edu.uniandes.csw.crud.spi.entity.PaymentStatus;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author r.calero
 */
public class ItemShoppingDTO implements Serializable{
    
    
    private Long quantity;
    
    
    /**
     * @generated
     */
    public ItemShoppingDTO() {
    }

    /**
     * Crea un objeto ItemShoppingDTO a partir de un objeto ItemShoppingEntity.
     *
     * @param entity Entidad ItemShoppingEntity desde la cual se va a crear el nuevo
     * objeto.
     * @generated
     */
    public ItemShoppingDTO(ItemShoppingEntity entity) {
        if (entity!=null)
        {
            this.quantity = entity.getQuantity();
            
        }
    }

    /**
     * Convierte un objeto ItemShoppingDTO a ItemShoppingEntity.
     *
     * @return Nueva objeto ItemShoppingEntity.
     * @generated
     */
    public ItemShoppingEntity toEntity() {
        ItemShoppingEntity entity = new ItemShoppingEntity();
        entity.setQuantity(this.getQuantity());
        return entity;
    }

  
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

   
    
}
