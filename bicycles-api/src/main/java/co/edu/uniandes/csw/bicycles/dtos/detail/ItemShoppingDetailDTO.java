/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.detail;


import co.edu.uniandes.csw.bicycles.dtos.minimum.*;
import co.edu.uniandes.csw.bicycles.entities.ItemShoppingEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author r.calero
 */
public class ItemShoppingDetailDTO extends ItemShoppingDTO{
    
    @PodamExclude
    private BicycleDTO bicycle;
    
    @PodamExclude
    private ShoppingDTO shopping;
    

    /**
     * @generated
     */
    public ItemShoppingDetailDTO() {
        super();
    }
    
    
    /**
     * Crea un objeto ItemShoppingDetailDTO a partir de un objeto ItemShopping incluyendo los atributos de BicycleDTO y ShoppingDTO.
     *
     * @param entity Entidad ItemShoppingEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ItemShoppingDetailDTO(ItemShoppingEntity entity) {
        super(entity);
        if (entity.getBicycle()!=null){
            this.bicycle = new BicycleDTO(entity.getBicycle());
        }
        if (entity.getShopping()!=null){
            this.shopping = new ShoppingDTO(entity.getShopping());
        }
    }
    
    /**
     * 
     *
     * @return Nueva objeto ItemShoppingEntity.
     * @generated
     */
    @Override
    public ItemShoppingEntity toEntity() {
        ItemShoppingEntity entity = super.toEntity();
        if (entity.getBicycle()!=null){
            entity.setBicycle(this.getBicycle().toEntity());
        }
        if (entity.getShopping()!=null){
            entity.setShopping(this.getShopping().toEntity());
        }
        return entity;
    }

    public BicycleDTO getBicycle() {
        return bicycle;
    }

    public void setBicycle(BicycleDTO bicycle) {
        this.bicycle = bicycle;
    }
    
    public ShoppingDTO getShopping() {
        return shopping;
    }

    public void setShopping(ShoppingDTO shopping) {
        this.shopping = shopping;
    }
}
