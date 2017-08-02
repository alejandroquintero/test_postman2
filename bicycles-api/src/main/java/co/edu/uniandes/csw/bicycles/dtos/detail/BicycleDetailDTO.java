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
package co.edu.uniandes.csw.bicycles.dtos.detail;

import co.edu.uniandes.csw.bicycles.dtos.minimum.*;
import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import co.edu.uniandes.csw.bicycles.entities.PhotoAlbumEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class BicycleDetailDTO extends BicycleDTO{


    @PodamExclude
    private CategoryDTO category;
    @PodamExclude
    private BrandDTO brand;
    @PodamExclude
    private List<PhotoAlbumDTO> photoAlbum;

    /**
     * @generated
     */
    public BicycleDetailDTO() {
        super();
    }

    /**
     * Crea un objeto BicycleDetailDTO a partir de un objeto BicycleEntity incluyendo los atributos de BicycleDTO.
     *
     * @param entity Entidad BicycleEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BicycleDetailDTO(BicycleEntity entity) {
        super(entity);
        if (entity.getCategory()!=null){
        this.category = new CategoryDTO(entity.getCategory());
        }
        if (entity.getBrand()!=null){
        this.brand = new BrandDTO(entity.getBrand());
        }
        if (entity.getPhotoAlbum()!=null){
          photoAlbum = new ArrayList<>();
          for(PhotoAlbumEntity photoEntity : entity.getPhotoAlbum()){
            photoAlbum.add(new PhotoAlbumDTO(photoEntity));
          }
        }
        
    }

    /**
     * Convierte un objeto BicycleDetailDTO a BicycleEntity incluyendo los atributos de BicycleDTO.
     *
     * @return Nueva objeto BicycleEntity.
     * @generated
     */
    @Override
    public BicycleEntity toEntity() {
        BicycleEntity entity = super.toEntity();
        if (this.getCategory()!=null){
        entity.setCategory(this.getCategory().toEntity());
        }
        if (this.getBrand()!=null){
        entity.setBrand(this.getBrand().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo category.
     *
     * @return atributo category.
     * @generated
     */
    public CategoryDTO getCategory() {
        return category;
    }

    /**
     * Establece el valor del atributo category.
     *
     * @param category nuevo valor del atributo
     * @generated
     */
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    /**
     * Obtiene el atributo brand.
     *
     * @return atributo brand.
     * @generated
     */
    public BrandDTO getBrand() {
        return brand;
    }

    /**
     * Establece el valor del atributo brand.
     *
     * @param brand nuevo valor del atributo
     * @generated
     */
    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

}
