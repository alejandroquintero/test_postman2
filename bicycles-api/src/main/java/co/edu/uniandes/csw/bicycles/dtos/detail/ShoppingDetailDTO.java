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
import co.edu.uniandes.csw.bicycles.entities.ShoppingEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ShoppingDetailDTO extends ShoppingDTO{

    @PodamExclude
    private BicycleDTO bicycle;
    
    @PodamExclude
    private ClientDTO client;
    

    /**
     * @generated
     */
    public ShoppingDetailDTO() {
        super();
    }

    /**
     * Crea un objeto BicycleDetailDTO a partir de un objeto BicycleEntity incluyendo los atributos de BicycleDTO.
     *
     * @param entity Entidad BicycleEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ShoppingDetailDTO(ShoppingEntity entity) {
        super(entity);
        if (entity.getBicycle()!=null){
            this.bicycle = new BicycleDTO(entity.getBicycle());
        }
        if (entity.getClient()!=null){
            this.client = new ClientDTO(entity.getClient());
        }
    }

    /**
     * 
     *
     * @return Nueva objeto BicycleEntity.
     * @generated
     */
    @Override
    public ShoppingEntity toEntity() {
        ShoppingEntity entity = super.toEntity();
        if (this.getBicycle()!=null){
            entity.setBicycle(this.getBicycle().toEntity());
        }
        if (this.getClient() !=null){
            entity.setClient(this.getClient().toEntity());
        }
        return entity;
    }

    public BicycleDTO getBicycle() {
        return bicycle;
    }

    public void setBicycle(BicycleDTO bicycle) {
        this.bicycle = bicycle;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}