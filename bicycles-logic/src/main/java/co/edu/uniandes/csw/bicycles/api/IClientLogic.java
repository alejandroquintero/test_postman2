/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.api;

import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import java.util.List;

/**
 *
 * @author Asistente
 */
public interface IClientLogic {
    
    public int countClients();
    public List<ClientEntity> getClients();
    public List<ClientEntity> getClients(Integer page, Integer maxRecords);
    public ClientEntity getClient(Long id);
    public ClientEntity createClient(ClientEntity entity);
    public ClientEntity updateClient(ClientEntity entity);
    public void deleteClient(Long id);
    
}