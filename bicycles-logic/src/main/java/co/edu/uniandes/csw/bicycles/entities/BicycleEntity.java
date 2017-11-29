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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Bicycle.getByDescription", query = "select u from BicycleEntity u Where UPPER(u.description) like :description")
    ,
    @NamedQuery(name = "Bicycle.getByStatus", query = "select u from BicycleEntity u Where UPPER(u.status) like :status"),
    @NamedQuery(name = "Bicycle.getLastBikes", query = "select u.id, u.description, u.name from BicycleEntity u ORDER BY u.creationDate")
})

public class BicycleEntity extends BaseEntity implements Serializable {

    private String description;
    private Long stock;
    private String color;
    private String status;
    private Double price;
    private Double discount;
    
    @PodamExclude
    @ManyToOne
    private BrandEntity brand;

    @PodamExclude
    @ManyToOne
    private CategoryEntity category;

    @PodamExclude
    @OneToMany(mappedBy = "bicycle", cascade = CascadeType.REMOVE)
    private List<PhotoAlbumEntity> photoAlbum = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "bicycle", cascade = CascadeType.REMOVE)
    private List<ReviewEntity> review = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    private java.util.Date creationDate;

    /**
     * Obtener precio.
     * @return precio.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Cambiar precio.
     * @param price precio.
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    /**
     * Obtiene el atributo description.
     *
     * @return atributo description.
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece el valor del atributo description.
     *
     * @param description nuevo valor del atributo
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtener el stock.
     * @return 
     */
    public Long getStock() {
        return stock;
    }

    /**
     * Camnbiar el stock.
     * @param stock 
     */
    public void setStock(Long stock) {
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
     * @param photoalbum nuevo valor de la colección.
     * @generated
     */
    public void setPhotoAlbum(List<PhotoAlbumEntity> photoalbum) {
        this.photoAlbum = photoalbum;
    }

    /**
     * Obtiene el valor del atributo status
     *
     * @return valor del atributo status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el valor del atributo status
     *
     * @param status nuevo valor del atributo status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * Obtener la fecha de creación.
     * @return Fecha de creación.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Fecha de creación del a Bibicleta.
     *
     * @param creationDate Fecha de creación.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Obtener color.
     * @return Color
     */
    public String getColor() {
        return color;
    }

    /**
     * Cambiar color
     * @param color Color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Review.
     * @return 
     */
    public List<ReviewEntity> getReview() {
        return review;
    }

    /**
     * Review.
     * @param review 
     */
    public void setReview(List<ReviewEntity> review) {
        this.review = review;
    }
    /**
     * Descuento.
     * @return discount
     */
    public Double getDiscount() {
        return discount;
    }
    /**
     * Cambiar discount
     * @param discount 
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    
    
}
