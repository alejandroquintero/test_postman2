/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.resources;

import co.edu.uniandes.csw.auth.filter.StatusCreated;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bicycles.api.IItemShoppingLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.ItemShoppingDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author r.calero
 */
@Path("/itemShoppings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ItemShoppingsResource {
    
    @Inject 
    private IItemShoppingLogic itemShoppingLogic;
    @Context 
    private HttpServletResponse response;
    @QueryParam("page") 
    private Integer page;
    @QueryParam("limit") 
    private Integer maxRecords;
    @PathParam("clientId") 
    private Long clientId;
    @PathParam("shoppingId") 
    private Long shoppingId;
    @PathParam("quantity") 
    private Long quantity;
    @PathParam("bicycleId") 
    private Long bicycleId;
    
    /**
     * Convierte una lista de ItemShoppingEntity a una lista de ItemShoppingDetailDTO.
     *
     * @param entityList Lista de ItemShoppingEntity a convertir.
     * @return Lista de ItemShoppingDetailDTO convertida.
     * @generated
     */
    private List<ItemShoppingDetailDTO> listEntity2DTO(List<ItemShoppingEntity> entityList) {
        List<ItemShoppingDetailDTO> list = new ArrayList<>();
        for (ItemShoppingEntity itemShopping : entityList) {
            list.add(new ItemShoppingDetailDTO(itemShopping));
        }
        return list;
    }
    
    /**
     * Obtiene la lista de los registros de ItemShopping
     *
     * @return Colección de objetos de ItemShoppingDetailDTO
     * @generated
     */
    @GET
    public List<ItemShoppingDetailDTO> getItemShopping() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", itemShoppingLogic.countItemShopping());
            return listEntity2DTO(itemShoppingLogic.getItemShopping(page, maxRecords));
        }
        return listEntity2DTO(itemShoppingLogic.getItemShoppingList());
    }
    
    
    /**
     * Obtiene los datos de una instancia de Shopping a partir de su ID
     *
     * @param itemShoppingId Identificador de la instancia a consultar
     * @return Instancia de ItemShoppingDetailDTO con los datos del ItemShopping consultado
     * @generated
     */
    @GET
    @Path("{itemShoppingId: \\d+}")
    public ItemShoppingDetailDTO getItemShopping(@PathParam("itemShoppingId") Long itemShoppingId) {
        return new ItemShoppingDetailDTO(itemShoppingLogic.getItemShopping(itemShoppingId));
    }

    @POST
    @StatusCreated
    public ItemShoppingDetailDTO createItemShopping(ItemShoppingDetailDTO dto) {
        return new ItemShoppingDetailDTO(itemShoppingLogic.createItemShopping(dto.toEntity()));
    }    
    
    @PUT
    @Path("{itemShoppingId: \\d+}")
    public ItemShoppingDetailDTO updateItemShopping(@PathParam("id") Long id, ItemShoppingDetailDTO dto) {
        ItemShoppingEntity entity = dto.toEntity();
        entity.setId(id);
        return new ItemShoppingDetailDTO(itemShoppingLogic.updateItemShopping(entity));
    }
    
    @DELETE
    @Path("{itemShoppingId: \\d+}")
    public void deleteItemShopping(@PathParam("id") Long id) {
        itemShoppingLogic.deleteItemShopping(id);
    }
}
