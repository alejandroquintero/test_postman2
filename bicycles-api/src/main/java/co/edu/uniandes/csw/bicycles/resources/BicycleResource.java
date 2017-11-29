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
import co.edu.uniandes.csw.bicycles.api.IBicycleLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.BicycleDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * URI: bicycles/
 *
 * @generated
 */
@Path("/bicycles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BicycleResource {

    @Inject
    private IBicycleLogic bicycleLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;
    @QueryParam("description")
    private String bicycleDescription;
    @QueryParam("promo")
    private String promo;
    @QueryParam("status")
    private String bicycleStatus;
    @QueryParam("username")
    private String username;


    /**
     * Convierte una lista de BicycleEntity a una lista de BicycleDetailDTO.
     *
     * @param entityList Lista de BicycleEntity a convertir.
     * @return Lista de BicycleDetailDTO convertida.
     * @generated
     */
    private List<BicycleDetailDTO> listEntity2DTO(List<BicycleEntity> entityList) {
        List<BicycleDetailDTO> list = new ArrayList<>();
        for (BicycleEntity entity : entityList) {
            list.add(new BicycleDetailDTO(entity));
        }
        return list;
    }

     @GET
    public List<BicycleDetailDTO> getLastBikes() {
    List<BicycleDetailDTO> ListLastBikes = null;
    List<BicycleDetailDTO> ListToReturn = null;
    Set<BicycleDetailDTO> newSet = new HashSet<>();
    
    //ultimas bicicletas
        
        ListLastBikes = listEntity2DTO(bicycleLogic.getLastBikes());
        // Store the first result set
        newSet = new HashSet<>(ListLastBikes);
        ListToReturn = new ArrayList<>(newSet);
        return ListToReturn;
    }
    
    
    /**
     * Obtiene la lista de los registros de Bicycle
     *
     * @return Colección de objetos de BicycleDetailDTO
     * @generated
     */
    @GET
    public List<BicycleDetailDTO> getBicycles() {

        // Initialize variables
        List<BicycleDetailDTO> ListByDescription = null; 
        List<BicycleDetailDTO> ListByStatus = null;
        List<BicycleDetailDTO> ListToReturn = null;
        Set<BicycleDetailDTO> newSet = new HashSet<>();
        
        if (bicycleDescription != null) {
            // Get results from logic
            ListByDescription = listEntity2DTO(bicycleLogic.getByDescription(bicycleDescription));
            // Store the first result set
            newSet = new HashSet<>(ListByDescription);
            ListToReturn = new ArrayList<>(newSet);
        }

        if (bicycleStatus != null) {
            // Get results from logic
            ListByStatus = listEntity2DTO(bicycleLogic.getByStatus(bicycleStatus));
            // Merge the second result set
            newSet.addAll(ListByStatus);
            ListToReturn = new ArrayList<>(newSet);
        }
        
        if (username != null) {
            // Get results from logic
            ListByDescription = listEntity2DTO(bicycleLogic.getFavorite(username));
            // Store the first result set
            newSet = new HashSet<>(ListByDescription);
            ListToReturn = new ArrayList<>(newSet);
        }

        if (promo != null) {
            List<BicycleDetailDTO> ListByDiscount = listEntity2DTO(bicycleLogic.getByDiscount());
            return ListByDiscount;
        }

        if (bicycleStatus == null && bicycleDescription == null && username == null && promo == null) {
            ListToReturn = listEntity2DTO(bicycleLogic.getBicycles());
        }

        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", bicycleLogic.countBicycles());
            ListToReturn = listEntity2DTO(bicycleLogic.getBicycles(page, maxRecords));
        }

        return ListToReturn;

    }

    /**
     * Obtiene los datos de una instancia de Bicycle a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BicycleDetailDTO con los datos del Bicycle
     * consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public BicycleDetailDTO getBicycle(@PathParam("id") Long id) {
        return new BicycleDetailDTO(bicycleLogic.getBicycle(id));
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
    public BicycleDetailDTO createBicycle(BicycleDetailDTO dto) {
        return new BicycleDetailDTO(bicycleLogic.createBicycle(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Bicycle
     *
     * @param id Identificador de la instancia de Bicycle a modificar
     * @param dto Instancia de BicycleDetailDTO con los nuevos datos
     * @return Instancia de BicycleDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public BicycleDetailDTO updateBicycle(@PathParam("id") Long id, BicycleDetailDTO dto) {
        BicycleEntity entity = dto.toEntity();
        entity.setId(id);
        return new BicycleDetailDTO(bicycleLogic.updateBicycle(entity));
    }

    /**
     * Elimina una instancia de Bicycle de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBicycle(@PathParam("id") Long id) {
        bicycleLogic.deleteBicycle(id);
    }

    public void existsBicycle(Long bicyclesId) {
        BicycleDetailDTO bicycle = getBicycle(bicyclesId);
        if (bicycle == null) {
            throw new WebApplicationException(404);
        }
    }

    @Path("{bicyclesId: \\d+}/photoAlbum")
    public Class<PhotoAlbumResource> getPhotoAlbumResource(@PathParam("bicyclesId") Long bicyclesId) {
        existsBicycle(bicyclesId);

        return PhotoAlbumResource.class;
    }

    @Path("{bicyclesId: \\d+}/review")
    public Class<ReviewResource> getReviewResource(@PathParam("bicyclesId") Long bicyclesId) {
        existsBicycle(bicyclesId);

        return ReviewResource.class;
    }
}
