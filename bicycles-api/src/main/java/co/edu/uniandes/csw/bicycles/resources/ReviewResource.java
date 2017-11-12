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
import co.edu.uniandes.csw.bicycles.api.IReviewLogic;
import co.edu.uniandes.csw.bicycles.dtos.detail.ReviewDetailDTO;
import co.edu.uniandes.csw.bicycles.entities.ReviewEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: bicycles/{bicyclesId: \\d+}/review
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewResource {

    @Inject private IReviewLogic reviewLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("bicyclesId") private Long bicyclesId;

   
    /**
     * Convierte una lista de ReviewEntity a una lista de ReviewDetailDTO
     *
     * @param entityList Lista de ReviewEntity a convertir
     * @return Lista de ReviewDetailDTO convertida
     * @generated
     */
    private List<ReviewDetailDTO> listEntity2DTO(List<ReviewEntity> entityList){
        List<ReviewDetailDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            list.add(new ReviewDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Review asociados a un Bicycle
     *
     * @return Colección de objetos de ReviewDetailDTO
     * @generated
     */
    @GET
    public List<ReviewDetailDTO> getReviews() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", reviewLogic.countReviews());
            return listEntity2DTO(reviewLogic.getReviews(page, maxRecords, bicyclesId));
        }
        return listEntity2DTO(reviewLogic.getReviews(bicyclesId));
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID asociado a un Bicycle
     *
     * @param reviewId Identificador de la instancia a consultar
     * @return Instancia de ReviewDetailDTO con los datos del Review consultado
     * @generated
     */
    @GET
    @Path("{reviewId: \\d+}")
    public ReviewDetailDTO getReview(@PathParam("reviewId") Long reviewId) {
        ReviewEntity entity = reviewLogic.getReview(reviewId);
        if (entity.getBicycle() != null && !bicyclesId.equals(entity.getBicycle().getId())) {
            throw new WebApplicationException(404);
        }
        return new ReviewDetailDTO(entity);
    }

    /**
     * Asocia un Review existente a un Bicycle
     *
     * @param dto Objeto de ReviewDetailDTO con los datos nuevos
     * @return Objeto de ReviewDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ReviewDetailDTO createReview(ReviewDetailDTO dto) {
        return new ReviewDetailDTO(reviewLogic.createReview(bicyclesId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param reviewId Identificador de la instancia de Review a modificar
     * @param dto Instancia de ReviewDetailDTO con los nuevos datos.
     * @return Instancia de ReviewDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{reviewId: \\d+}")
    public ReviewDetailDTO updateReview(@PathParam("reviewId") Long reviewId, ReviewDetailDTO dto) {
        ReviewEntity entity = dto.toEntity();
        entity.setId(reviewId);
        return new ReviewDetailDTO(reviewLogic.updateReview(bicyclesId, entity));
    }

    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param reviewId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("reviewId: \\d+}")
    public void deleteReview(@PathParam("reviewId") Long reviewId) {
        reviewLogic.deleteReview(reviewId);
    }
}
