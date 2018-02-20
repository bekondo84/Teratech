/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.callbackhandler;

import com.megatim.security.callback.LoginCallBack;
import com.megatim.security.callback.PasswordCallBack;
import com.megatim.security.clients.Authentification;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author user
 */
public class AuthentificationCallBackHandler implements CallbackHandler {
    
    private Authentification frame;
    
    public AuthentificationCallBackHandler() {
        
    }
    
    public AuthentificationCallBackHandler(Authentification frame) {
        this.frame = frame;
    }
    
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        
        if (callbacks[0] instanceof LoginCallBack) {
                
            LoginCallBack login = (LoginCallBack)callbacks[0];
            login.setLogin(this.frame.getLogin().getText());
                
         }
        
        if(callbacks[1] instanceof PasswordCallBack){
                
            PasswordCallBack password = (PasswordCallBack)callbacks[1];
            password.setPassword(this.frame.getPassword().getText());
       
         }
         
    }
    
}
