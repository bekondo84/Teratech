/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.loginmodules;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.services.IocContext;
import com.megatim.security.callback.LoginCallBack;
import com.megatim.security.callback.PasswordCallBack;
import com.megatim.security.cryptographie.symetrique.DESEncrypter;
import com.megatim.security.manager.connection.ManagerConnection;
import com.megatim.security.model.Autorisation;
import com.megatim.security.model.Utilisateur;
import com.megatim.security.principal.UserPrincipal;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author user
 */
public class LoginModuleWithEncrypt implements LoginModule {

    // initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    // configurable option
    private String debugTestRecuperer1;
    private String debugTestRecuperer2;

    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    //Iocontext
    private IocContext ctx = new IocContext();

    //Manager generique
    private GenericManager manager;

    // Principal
    UserPrincipal userPrincipal;

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        // initialize any configured options
        debugTestRecuperer1 = (String) options.get("debugTest1");
        debugTestRecuperer2 = (String) options.get("debugTest2");
    }

    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new LoginCallBack();
        callbacks[1] = new PasswordCallBack();

        try {

            callbackHandler.handle(callbacks);

            String username = ((LoginCallBack) callbacks[0]).getLogin();
            String password = ((PasswordCallBack) callbacks[1]).getPassword();

            if (ifAuthentificated(username, password)) {

                succeeded = true;
                commitSucceeded = true;

            } else {

                System.out.println("login Failed");
                succeeded = false;
                commitSucceeded = false;
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginModuleWithEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCallbackException ex) {
            Logger.getLogger(LoginModuleWithEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return succeeded;
    }

    public boolean commit() throws LoginException {
        
        //On verifie si ce principal n'existe pas deja
        if (!ManagerConnection.subject.getPrincipals().contains(userPrincipal)) {

            //on ajoute le principal dans le subject
            ManagerConnection.subject.getPrincipals().add(userPrincipal);

            //On Definit cette utilisateur comme utilisateur actuellement connecté
            ManagerConnection.currentUser = userPrincipal;
        }

        return commitSucceeded;
    }

    public boolean abort() throws LoginException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean logout() throws LoginException {
        //On verifie si ce principal n'existe pas deja
        if (ManagerConnection.subject.getPrincipals().contains(userPrincipal)) {
            System.out.println(" ===================== logout() : SUPPRESSION DU USER CONNECTE ======================== " + ((UserPrincipal) (ManagerConnection.subject.getPrincipals().iterator().next())).getUserConnected().getNom());
            //On retire de la liste
            ManagerConnection.subject.getPrincipals().remove(userPrincipal);

            //On detruit l'objet
            ManagerConnection.currentUser = null;

            return true;
        } else {
            return false;
        }
    }

    private boolean ifAuthentificated(String login, String password) {

        try {
            manager = (GenericManager) ctx.lookup("com.megatim.security.core.impl.Utilisateur.UtilisateurManagerImpl");

            List<Utilisateur> liste = manager.findByUniqueProperty("login", login, null);

            if (!liste.isEmpty() && liste != null) {

                //On decrypte le mot de passe
                String motDePasseDecrypter = DESEncrypter.getInstance().decryptText(liste.get(0).getPassword());

                if (motDePasseDecrypter.equals(password)) {

                    this.userPrincipal = new UserPrincipal(login, liste.get(0));
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginModuleWithEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
