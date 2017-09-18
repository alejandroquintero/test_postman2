/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.persistence;

import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Asistente
 */
@Stateless
public class ClientPersistence  extends CrudPersistence<ClientEntity>{
    @PersistenceContext(unitName="BicyclesPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    protected Class<ClientEntity> getEntityClass() {
        return ClientEntity.class;
    }
}