/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.resources;

import co.edu.uniandes.csw.auth.filter.StatusCreated;
import co.edu.uniandes.csw.bicycles.api.IClientLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.ClientDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author jd.runza
 */
@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    
    @Inject private IClientLogic clientLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<ClientDetailDTO> listEntity2DTO(List<ClientEntity> entityList){
        List<ClientDetailDTO> list = new ArrayList<>();
        for (ClientEntity entity : entityList) {
            list.add(new ClientDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<ClientDetailDTO> getClients() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", clientLogic.countClients());
            return listEntity2DTO(clientLogic.getClients(page, maxRecords));
        }
        return listEntity2DTO(clientLogic.getClients());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ClientDetailDTO getClient(@PathParam("id") Long id) {
        return new ClientDetailDTO(clientLogic.getClient(id));
    }
    
    @POST
    @StatusCreated
    public ClientDetailDTO createClient(ClientDetailDTO dto) {
        return new ClientDetailDTO(clientLogic.createClient(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ClientDetailDTO  updateClient(@PathParam("id") Long id, ClientDetailDTO dto) {
        ClientEntity entity = dto.toEntity();
        entity.setId(id);
        return new ClientDetailDTO(clientLogic.updateClient(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteClient(@PathParam("id") Long id) {
        clientLogic.deleteClient(id);
    }
    
    @Path("{clientId: \\d+}/shopping")
    public Class<ShoppingResource> getShoppingResource(@PathParam("clientId") Long clientId) {
        existsClient(clientId);
        return ShoppingResource.class;
    }
    
    public void existsClient(Long clientId) {
        ClientDetailDTO client = getClient(clientId);
        if (client == null) {
            throw new WebApplicationException(404);
        }
    }
}