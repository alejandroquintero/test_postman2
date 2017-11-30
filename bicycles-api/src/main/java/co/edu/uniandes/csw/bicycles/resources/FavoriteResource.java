/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.resources;

import co.edu.uniandes.csw.auth.filter.StatusCreated;
import co.edu.uniandes.csw.bicycles.api.IFavoriteLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.FavoriteDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.FavoriteEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cc.huertas
 */
@Path("/favorites")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FavoriteResource {
    @Inject private IFavoriteLogic favoriteLogic;
    @Context private HttpServletResponse response;
    @QueryParam("bicycleId") private Long bicycleId;
    @QueryParam("username") private String username;
    
    
    private List<FavoriteDetailDTO> listEntity2DTO(List<FavoriteEntity> entityList){
        List<FavoriteDetailDTO> list = new ArrayList<>();
        for (FavoriteEntity entity : entityList) {
            list.add(new FavoriteDetailDTO(entity));
        }
        return list;
    }
    
    @GET
    public List<FavoriteDetailDTO> getFavorite() {
        if (username != null) {
            return listEntity2DTO(favoriteLogic.getFavoriteClient(username));
        }
        return listEntity2DTO(new ArrayList<FavoriteEntity>());
    }
    
    @POST
    @StatusCreated
    public FavoriteDetailDTO createFavorite(FavoriteDetailDTO dto) {
        return new FavoriteDetailDTO(favoriteLogic.createFavorite(new Long(dto.getBicycleId()), dto.getUsername()));
    }
    
    @DELETE
    @Path("{bicycleId: \\d+}/{username: \\.}")
    public void deleteFavorite(@PathParam("bicyclesId") Long bicyclesId, @PathParam("username") String username) {
        favoriteLogic.deleteFavorite(bicyclesId, username);
    }
    
}
