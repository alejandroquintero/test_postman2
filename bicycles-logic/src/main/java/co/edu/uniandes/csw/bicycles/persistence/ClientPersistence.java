/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.persistence;

import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import java.util.logging.Logger;

/**
 *
 * @author Asistente
 */
@Stateless
public class ClientPersistence extends CrudPersistence<ClientEntity> {

    @PersistenceContext(unitName = "BicyclesPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Class<ClientEntity> getEntityClass() {
        return ClientEntity.class;
    }

    /**
     * Obtener cliente por login
     *
     * @param login
     * @return cliente
     */
    public ClientEntity getByLogin(String login) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("login", login);
            return executeSingleNamedQuery("Client.getByLogin", params);
        } catch (NoResultException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
            return null;
        }
    }
}
