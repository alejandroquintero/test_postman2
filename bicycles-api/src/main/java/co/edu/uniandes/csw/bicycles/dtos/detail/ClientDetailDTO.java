/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.detail;

import co.edu.uniandes.csw.bicycles.dtos.minimum.ClientDTO;
import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asistente
 */
@XmlRootElement
public class ClientDetailDTO extends ClientDTO {
     public ClientDetailDTO() {
        super();
    }
    
    public ClientDetailDTO(ClientEntity entity) {
        super(entity);
        
    }
    
    @Override
    public ClientEntity toEntity() {
        ClientEntity entity = super.toEntity();
        return entity;
    }
}