/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.ejbs;

import co.edu.uniandes.csw.bicycles.api.IBicycleLogic;
import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.api.IFavoriteLogic;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;
import co.edu.uniandes.csw.bicycles.persistence.FavoritePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cc.huertas
 */
public class FavoriteLogic implements IFavoriteLogic{
    
    @Inject private FavoritePersistence persistence;
    @Inject private IClientLogic clientLogic;
    @Inject private IBicycleLogic bicycleLogic;

    @Override
    public FavoriteEntity createFavorite(Long idBicycle, String username) {
        BicycleEntity bicycleEntity = bicycleLogic.getBicycle(idBicycle);
        ClientEntity clientEntity = clientLogic.getClient(username);
        
        FavoriteEntity entity = new FavoriteEntity();
        entity.setBicycle(bicycleEntity);
        entity.setClient(clientEntity);
        return persistence.create(entity);
    }

    @Override
    public void deleteFavorite(Long idBicycle, String username) {
        BicycleEntity bicycleEntity = bicycleLogic.getBicycle(idBicycle);
        ClientEntity clientEntity = clientLogic.getClient(username);
        
        FavoriteEntity entity = persistence.find(idBicycle, clientEntity.getId());
        
        persistence.delete(entity.getId());
    }

    @Override
    public List<FavoriteEntity> getFavoriteClient(String username) {
        ClientEntity client = clientLogic.getClient(username);
        List<FavoriteEntity> favorites = persistence.getByUser(client);
        return favorites;
    }
}
