/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.ejbs;

import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.api.IFavoriteLogic;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;
import co.edu.uniandes.csw.bicycles.persistence.FavoritePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author cc.huertas
 */
public class FavoriteLogic implements IFavoriteLogic{
    
    @Inject private FavoritePersistence persistence;
    @Inject private IClientLogic clientLogic;

    @Override
    public int countFavoriteClient(String username) {
        ClientEntity client = clientLogic.getClient(username);
        return persistence.countByUser(client);
    }

    @Override
    public FavoriteEntity createFavorite(FavoriteEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public void deleteFavorite(FavoriteEntity entity) {
        persistence.delete(new Long(1));
    }

    @Override
    public List<FavoriteEntity> getFavoriteClient(String username) {
        ClientEntity client = clientLogic.getClient(username);
        List<FavoriteEntity> favorites = persistence.getByUser(client);
        return favorites;
    }
}
