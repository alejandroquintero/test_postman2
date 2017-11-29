/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.api;

import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;
import java.util.List;

/**
 *
 * @author cc.huertas
 */
public interface IFavoriteLogic {
    public int countFavoriteClient(String username);
    public List<FavoriteEntity> getFavoriteClient(String username);
    public FavoriteEntity createFavorite(Long bicycleId, String username);
    public void deleteFavorite(Long bicycleId, String username);
}
