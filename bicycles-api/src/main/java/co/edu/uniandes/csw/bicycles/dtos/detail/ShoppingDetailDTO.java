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
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class ShoppingDetailDTO extends ShoppingDTO{
    
    @PodamExclude
    private ClientDTO client;
    @PodamExclude
    private List<ItemShoppingDTO> itemShopping;
    

    /**
     * @generated
     */
    public ShoppingDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ShoppingDetailDTO a partir de un objeto ClientEntity incluyendo los atributos de ItemShoppingDTO.
     *
     * @param entity Entidad ShoppingEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ShoppingDetailDTO(ShoppingEntity entity) {
        super(entity);
        if (entity.getClient()!=null){
            this.client = new ClientDTO(entity.getClient());
        }
        if (entity.getItemShopping()!=null){
          itemShopping = new ArrayList<>();
          for(ItemShoppingEntity ishop : entity.getItemShopping()){
            itemShopping.add(new ItemShoppingDTO(ishop));
          }
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
        if (this.getClient() !=null){
            entity.setClient(this.getClient().toEntity());
        }
        return entity;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}