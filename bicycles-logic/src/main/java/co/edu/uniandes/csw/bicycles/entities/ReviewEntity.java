/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ra.gomez11
 */
@Entity
public class ReviewEntity extends BaseEntity implements Serializable {

    private String commentary;
    
    private Integer star;
    
    @PodamExclude
    @ManyToOne
    private BicycleEntity bicycle;

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public BicycleEntity getBicycle() {
        return bicycle;
    }

    public void setBicycle(BicycleEntity bicycle) {
        this.bicycle = bicycle;
    }
}
