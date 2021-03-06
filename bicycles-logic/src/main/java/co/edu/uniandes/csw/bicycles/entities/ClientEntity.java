/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bicycles.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Asistente
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Client.getByLogin", query = "select u from ClientEntity u Where u.login = :login")
})

public class ClientEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<ShoppingEntity> shopping = new ArrayList<>();
    
    private String lastName;
    private String firstName;
    private String login;
    private String phone;
    private String address;
    private String email;
    private String idAuth0;

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

    public List<ShoppingEntity> getShopping() {
        return shopping;
    }

    public void setShopping(List<ShoppingEntity> shopping) {
        this.shopping = shopping;
    } 
}