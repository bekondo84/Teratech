/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.model.test;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author DEV_4
 */
@Entity
public class CompteUser {
    
    @Id
    private String login ;   
    
    @Column(nullable = false,length = 12)
    private String password ;

    private double age ;   
    
    private CompteUser user ;
    
    private Boolean desactiver = Boolean.FALSE;
   
   private List<CompteUser> values ;
    
    public CompteUser() {
    }

    public CompteUser(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

  
    

    @Override
    public String toString() {
        return "CompteUser{" + "login=" + login + ", password=" + password + ", age=" + age + '}';
    }
    
    
}
