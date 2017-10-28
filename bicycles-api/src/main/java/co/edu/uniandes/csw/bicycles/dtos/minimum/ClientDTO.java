/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.dtos.minimum;

import co.edu.uniandes.csw.bicycles.entities.ClientEntity;
import java.io.Serializable;

/**
 *
 * @author Asistente
 */
public class ClientDTO implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String firstName;
    private String login;
    private String phone;
    private String address;
    private String email;
    private String idAuth0;

    public ClientDTO() {
    }

    public ClientDTO(ClientEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.lastName = entity.getLastName();
            this.firstName = entity.getFirstName();
            this.login = entity.getLogin();
            this.phone = entity.getPhone();
            this.address = entity.getAddress();
            this.email = entity.getEmail();
            this.idAuth0 = entity.getIdAuth0();
        }
    }

    public ClientEntity toEntity() {
        ClientEntity entity = new ClientEntity();
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setLastName(this.getLastName());
        entity.setFirstName(this.getFirstName());
        entity.setLogin(this.getLogin());
        entity.setPhone(this.getPhone());
        entity.setAddress(this.getAddress());
        entity.setEmail(this.getEmail());
        entity.setIdAuth0(this.getIdAuth0());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
    
    public String getIdAuth0() {
        return idAuth0;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdAuth0(String idAuth0) {
        this.idAuth0 = idAuth0;
    }
}
