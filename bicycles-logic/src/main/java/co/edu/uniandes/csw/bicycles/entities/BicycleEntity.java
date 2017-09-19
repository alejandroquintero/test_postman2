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
package co.edu.uniandes.csw.bicycles.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @generated
 */
@Entity
public class BicycleEntity extends BaseEntity implements Serializable {

    private String description;
    private Long stock;

    @PodamExclude
    @ManyToOne
    private BrandEntity brand;

    @PodamExclude
    @ManyToOne
    private CategoryEntity category;

    @PodamExclude
    @OneToMany(mappedBy = "bicycle", cascade = CascadeType.REMOVE)
    private List<PhotoAlbumEntity> photoAlbum = new ArrayList<>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private java.util.Date creationDate;

    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription(){
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description){
        this.description = description;
    }

    // atributo nuevo
    public Long getStock(){
        return stock;
    }
    // atributo nuevo
    public void setStock(Long stock){
        this.stock = stock;
    }
    
    /**
     * Obtiene el atributo brand.
     *
     * @return atributo brand.
     * @generated
     */
    public BrandEntity getBrand() {
        return brand;
    }

    /**
     * Establece el valor del atributo brand.
     *
     * @param brand nuevo valor del atributo
     * @generated
     */
    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    /**
     * Obtiene el atributo category.
     *
     * @return atributo category.
     * @generated
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * Establece el valor del atributo category.
     *
     * @param category nuevo valor del atributo
     * @generated
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    /**
     * Obtiene la colección de photoAlbum.
     *
     * @return colección photoAlbum.
     * @generated
     */
    public List<PhotoAlbumEntity> getPhotoAlbum() {
        return photoAlbum;
    }

    /**
     * Establece el valor de la colección de photoAlbum.
     *
     * @param photoAlbum nuevo valor de la colección.
     * @generated
     */
    public void setPhotoAlbum(List<PhotoAlbumEntity> photoalbum) {
        this.photoAlbum = photoalbum;
    }

    /**
     * Obtener la fecha de creación.
     * @return Fecha de creación.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Fecha de creación del a Bibicleta.
     * @param creationDate Fecha de creación.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
