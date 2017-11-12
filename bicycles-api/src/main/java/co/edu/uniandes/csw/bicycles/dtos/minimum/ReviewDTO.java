package co.edu.uniandes.csw.bicycles.dtos.minimum;

import co.edu.uniandes.csw.bicycles.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDTO implements Serializable {

    private Long id;
    private Integer star;
    private String commentary;

    /**
     * @generated
     */
    public ReviewDTO() {
    }

    /**
     * Crea un objeto ReviewDTO a partir de un objeto ReviewEntity.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo
     * objeto.
     * @generated
     */
    public ReviewDTO(ReviewEntity entity) {
        if (entity != null) {
            this.star = entity.getStar();
            this.id = entity.getId();
            this.commentary = entity.getCommentary();
        }
    }

    /**
     * Convierte un objeto ReviewDTO a ReviewEntity.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    public ReviewEntity toEntity() {
        ReviewEntity entity = new ReviewEntity();
        entity.setStar(this.getStar());
        entity.setId(this.getId());
        entity.setCommentary(this.getCommentary());
        return entity;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
