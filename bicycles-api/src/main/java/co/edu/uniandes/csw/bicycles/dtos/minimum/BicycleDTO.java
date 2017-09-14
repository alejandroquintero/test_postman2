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
package co.edu.uniandes.csw.bicycles.dtos.minimum;

import co.edu.uniandes.csw.bicycles.entities.BicycleEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @generated
 */
@XmlRootElement
public class BicycleDTO  implements Serializable{

    private String description;
    private Long id;
    private String name;
    private Long stock;


    /**
     * @generated
     */
    public BicycleDTO() {
    }

    /**
     * Crea un objeto BicycleDTO a partir de un objeto BicycleEntity.
     *
     * @param entity Entidad BicycleEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BicycleDTO(BicycleEntity entity) {
	   if (entity!=null){
        this.description=entity.getDescription();
        this.id=entity.getId();
        this.name=entity.getName();
        this.stock=entity.getStock();
       }
    }

    /**
     * Convierte un objeto BicycleDTO a BicycleEntity.
     *
     * @return Nueva objeto BicycleEntity.
     * @generated
     */
    public BicycleEntity toEntity() {
        BicycleEntity entity = new BicycleEntity();
        entity.setDescription(this.getDescription());
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setStock(this.getStock());
    return entity;
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
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    // atributo nuevo
    public Long getStock(){
        return stock; 
    }
    
    // atributo nuevo
    public void setStock(Long stock){
        this.stock = stock;
    }

}
