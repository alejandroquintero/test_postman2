package co.edu.uniandes.csw.bicycles.dtos.detail;

import co.edu.uniandes.csw.bicycles.dtos.minimum.*;
import co.edu.uniandes.csw.bicycles.entities.ReviewEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ReviewDetailDTO extends ReviewDTO {

    @PodamExclude
    private BicycleDTO bicycle;

    /**
     * @generated
     */
    public ReviewDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ReviewDetailDTO a partir de un objeto ReviewEntity
     * incluyendo los atributos de ReviewDTO.
     *
     * @param entity Entidad ReviewEntity desde la cual se va a crear el nuevo
     * objeto.
     * @generated
     */
    public ReviewDetailDTO(ReviewEntity entity) {
        super(entity);
        if (entity.getBicycle() != null) {
            this.bicycle = new BicycleDTO(entity.getBicycle());
        }

    }

    /**
     * Convierte un objeto ReviewDetailDTO a ReviewEntity incluyendo los
     * atributos de ReviewDTO.
     *
     * @return Nueva objeto ReviewEntity.
     * @generated
     */
    @Override
    public ReviewEntity toEntity() {
        ReviewEntity entity = super.toEntity();
        if (this.getBicycle() != null) {
            entity.setBicycle(this.getBicycle().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo bicycle.
     *
     * @return atributo bicycle.
     * @generated
     */
    public BicycleDTO getBicycle() {
        return bicycle;
    }

    /**
     * Establece el valor del atributo bicycle.
     *
     * @param bicycle nuevo valor del atributo
     * @generated
     */
    public void setBicycle(BicycleDTO bicycle) {
        this.bicycle = bicycle;
    }
}
