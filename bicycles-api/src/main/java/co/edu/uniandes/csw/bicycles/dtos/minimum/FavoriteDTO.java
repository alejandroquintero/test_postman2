/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.minimum;

import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;

/**
 *
 * @author cc.huertas
 */
public class FavoriteDTO {
    private Long id;
    private Long bicycleId;
    private Long clientId;
    private String username;

    public FavoriteDTO() {
    }
    
    public FavoriteDTO(FavoriteEntity entity) {
	if (entity!=null){
            this.id=entity.getId();
            this.bicycleId = entity.getBicycle().getId();
            this.clientId = entity.getClient().getId();
       }
    }
     
     public FavoriteEntity toEntity() {
        FavoriteEntity entity = new FavoriteEntity();
        entity.setId(this.getId());
        
        BicycleEntity bicycleEntity = new BicycleEntity();
        bicycleEntity.setId(this.getBicycleId());
        entity.setBicycle(bicycleEntity);
      
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(this.getClientId());
        entity.setClient(clientEntity);
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Long bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
