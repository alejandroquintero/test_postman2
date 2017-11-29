/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.detail;

import co.edu.uniandes.csw.bicycles.dtos.minimum.FavoriteDTO;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;

/**
 *
 * @author cc.huertas
 */
public class FavoriteDetailDTO extends FavoriteDTO{
    public FavoriteDetailDTO() {
        super();
    }
    
    public FavoriteDetailDTO(FavoriteEntity entity) {
        super(entity);
        
    }
    
    @Override
    public FavoriteEntity toEntity() {
        FavoriteEntity entity = super.toEntity();
        return entity;
    }
}
