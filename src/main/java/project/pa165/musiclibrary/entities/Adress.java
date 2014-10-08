/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Milan
 */
@Embeddable
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 60)
    private String city;
    
    @Column(length = 120)
    private String street;
    
    @Column(length = 70)
    private String state;
    
    @Column(length = 10)
    private Integer zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
