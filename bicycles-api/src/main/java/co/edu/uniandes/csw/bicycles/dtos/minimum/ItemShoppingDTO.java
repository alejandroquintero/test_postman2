/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.minimum;


import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import java.io.Serializable;

/**
 *
 * @author r.calero
 */
public class ItemShoppingDTO implements Serializable{
    
    
    private Long quantity;
    private Long bicycleId;
    private String clientId;
    
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
            //this.bicycleId = entity.getBicycle().getId();
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
        BicycleEntity bici = new BicycleEntity();
        bici.setId(getBicycleId());
        
        entity.setQuantity(this.getQuantity());
        entity.setBicycle(bici);
        entity.setTempUser(getClientId());
        return entity;
    }

  
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
