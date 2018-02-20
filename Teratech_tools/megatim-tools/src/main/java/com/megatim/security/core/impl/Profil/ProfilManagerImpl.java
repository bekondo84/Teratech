
package com.megatim.security.core.impl.Profil;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.Transaction;
import com.megatim.security.core.ifaces.Profil.ProfilManager;
import com.megatim.security.model.Profil;


/**
 * Classe d'implementation du manager

 * @since Sun Sep 18 21:53:33 CEST 2016
 * 
 */
@Transaction
public class ProfilManagerImpl
    extends AbstractGenericManager<Profil, String>
    implements ProfilManager
{

    /**
     * On injecte la DAO
     * 
     */
    @Inject("com.megatim.security.dao.impl.Profil.ProfilDAOImpl")
    protected GenericDAO dao;

    public ProfilManagerImpl() {
    }

    /**
     * Methode permettant de retourner la DAO
     * 
     */
    @Override
    public GenericDAO<Profil, String> getDao() {
        return dao;
    }

    /**
     * Methode permettant de retourner l'id de l'entite
     * 
     */
    @Override
    public String getEntityIdName() {
        return "nom";
    }

}
