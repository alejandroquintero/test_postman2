/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bicycles.resources;

import co.edu.uniandes.csw.auth.filter.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bicycles.api.IShoppingLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.ShoppingDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import java.util.ArrayList;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;

/**
 * URI: clients/{clientId: \\d+}/shopping
 * @generated
 */
@Path("/shopping")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShoppingResource {

    @Inject private IShoppingLogic shoppingLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @QueryParam("clientId") private Long clientId;
   
    /**
     * Convierte una lista de ShoppingEntity a una lista de ShoppingDetailDTO.
     *
     * @param entityList Lista de ShoppingEntity a convertir.
     * @return Lista de ShoppingDetailDTO convertida.
     * @generated
     */
    private List<ShoppingDetailDTO> listEntity2DTO(List<ShoppingEntity> entityList){
        List<ShoppingDetailDTO> list = new ArrayList<>();
        for (ShoppingEntity entity : entityList) {
            list.add(new ShoppingDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Shopping
     *
     * @return Colección de objetos de ShoppingDetailDTO
     * @generated
     */
    @GET
    public List<ShoppingDetailDTO> getShopping() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", shoppingLogic.countShopping());
            return listEntity2DTO(shoppingLogic.getShopping(page, maxRecords, clientId));
        }
        return listEntity2DTO(shoppingLogic.getShoppingList(new Long(1)));
    }

    /**
     * Obtiene los datos de una instancia de Shopping a partir de su ID
     *
     * @param shoppingId
     * @return Instancia de ShoppingDetailDTO con los datos del Shopping consultado
     * @generated
     */
    @GET
    @Path("{shoppingId: \\d+}")
    public ShoppingDetailDTO getShopping(@PathParam("shoppingId") Long shoppingId) {
        ShoppingEntity entity = shoppingLogic.getShopping(shoppingId);
        if (entity.getClient() != null) {
            if (!clientId.equals(entity.getClient().getId())) {
                throw new WebApplicationException(404);
            }
        }
        return new ShoppingDetailDTO(entity);
    }
    
    
    /**
     * Se encarga de crear un Bicycle en la base de datos
     *
     * @param dto Objeto de BicycleDetailDTO con los datos nuevos
     * @return Objeto de BicycleDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public ShoppingDetailDTO createShopping(ShoppingDetailDTO dto) {
        return new ShoppingDetailDTO(shoppingLogic.createShopping(dto.getClient().getId(), dto.toEntity()));
    }
    
    /**
     * Devuelve la cantidad de productos en el carro
     *
     * @return 
     * @generated
     */
    @GET
    @Path("/count")
    public ShoppingDetailDTO countItemShopping() {
        return new ShoppingDetailDTO(shoppingLogic.getShoppingCar(clientId));
    }
    
    /**
     * realiza la compra del shopping car
     * 
     * @generated
     */
    @GET
    @Path("/checkout")
    public void checkout() {
        System.out.print(clientId);
        shoppingLogic.checkoutShoppingCar(clientId);
    }
}
