/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.callback;

import javax.security.auth.callback.Callback;

/**
 *
 * @author user
 */
public class PasswordCallBack implements Callback{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
