/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author MGT
 */
@Entity
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="ADRESSE")
    private String addresse =null ;
    @Column(name="PHONE")
    private String phone ;
    @Column(name="E_MAIL")
    private String email ;

    
    public Contact() {
    }

    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.addresse != null ? this.addresse.hashCode() : 0);
        hash = 37 * hash + (this.phone != null ? this.phone.hashCode() : 0);
        hash = 37 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if ((this.addresse == null) ? (other.addresse != null) : !this.addresse.equals(other.addresse)) {
            return false;
        }
        if ((this.phone == null) ? (other.phone != null) : !this.phone.equals(other.phone)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "com.bekosoftware.genericdaolayer.Contact[ id=" + id + " ]";
    }
    
}
