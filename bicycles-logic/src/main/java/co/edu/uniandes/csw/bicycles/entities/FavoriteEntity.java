/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author cc.huertas
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Favorite.getByUser", query = "select u from FavoriteEntity u Where u.client.id = :idClient"),
    @NamedQuery(name = "Favorite.countByUser", query = "select count(u) from FavoriteEntity u Where u.client.id = :idClient"),
    @NamedQuery(name = "Favorite.findCons", query = "select u from FavoriteEntity u Where u.client.id = :idClient and u.bicycle.id = :idBicycle")
}) 
public class FavoriteEntity extends BaseEntity implements Serializable {
    @PodamExclude
    @OneToOne
    private ClientEntity client;
    
    @PodamExclude
    @OneToOne
    private BicycleEntity bicycle;

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public BicycleEntity getBicycle() {
        return bicycle;
    }

    public void setBicycle(BicycleEntity bicycle) {
        this.bicycle = bicycle;
    }
}
