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
public class LoginCallBack implements Callback{
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
