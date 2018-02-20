
package com.megatim.security.core.impl.Utilisateur;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.Transaction;
import com.megatim.security.core.ifaces.Utilisateur.UtilisateurManager;
import com.megatim.security.model.Utilisateur;

/**
 * Classe d'implémentation du manager
 * @since Thu Aug 25 16:24:42 WAT 2016
 * 
 */
@Transaction
public class UtilisateurManagerImpl    extends AbstractGenericManager<Utilisateur, String>
    implements UtilisateurManager
{

    /**
     * On injecte la DAO
     * 
     */
    @Inject("com.megatim.security.dao.impl.Utilisateur.UtilisateurDAOImpl")
    protected GenericDAO dao;
    
    public UtilisateurManagerImpl() {
        
    }

    /**
     * Methode permettant de retourner la DAO
     * 
     */
    @Override
    public GenericDAO<Utilisateur, String> getDao() {
        return dao;
    }

    /**
     * Methode permettant de retourner l'id de l'entité
     * 
     */
    @Override
    public String getEntityIdName() {
        return "login";
    }
    
}
