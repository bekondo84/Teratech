
package com.megatim.security.core.impl.Autorisation;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.Inject;
import com.megatim.common.annotations.Transaction;
import com.megatim.security.core.ifaces.Autorisation.AutorisationManager;
import com.megatim.security.model.Autorisation;


/**
 * Classe d'implementation du manager

 * @since Sun Sep 18 21:53:33 CEST 2016
 * 
 */
@Transaction
public class AutorisationManagerImpl
    extends AbstractGenericManager<Autorisation, Long>
    implements AutorisationManager
{

    /**
     * On injecte la DAO
     * 
     */
    @Inject("com.megatim.security.dao.impl.Autorisation.AutorisationDAOImpl")
    protected GenericDAO dao;

    public AutorisationManagerImpl() {
    }

    /**
     * Methode permettant de retourner la DAO
     * 
     */
    @Override
    public GenericDAO<Autorisation, Long> getDao() {
        return dao;
    }

    /**
     * Methode permettant de retourner l'id de l'entite
     * 
     */
    @Override
    public String getEntityIdName() {
        return "id";
    }

}
