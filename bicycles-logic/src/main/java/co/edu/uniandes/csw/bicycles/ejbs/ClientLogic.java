/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.ejbs;

import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import co.edu.uniandes.csw.bicycles.persistence.ClientPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jd.runza
 */
public class ClientLogic implements IClientLogic {

    @Inject
    private ClientPersistence persistence;

    @Override
    public int countClients() {
        return persistence.count();
    }

    @Override
    public List<ClientEntity> getClients() {
        return persistence.findAll();
    }

    @Override
    public List<ClientEntity> getClients(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    @Override
    public ClientEntity getClient(Long id) {
        return persistence.find(id);
    }

    @Override
    public ClientEntity createClient(ClientEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public ClientEntity updateClient(ClientEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteClient(Long id) {
        persistence.delete(id);
    }

}
