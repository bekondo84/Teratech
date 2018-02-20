
package com.megatim.security.dao.impl.Autorisation;

import javax.persistence.EntityManager;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.megatim.common.annotations.PersistenceManager;
import com.megatim.security.dao.ifaces.Autorisation.AutorisationDAO;
import com.megatim.security.model.Autorisation;


/**
 * Classe d'implementation de la DAO

 * @since Sun Sep 18 21:53:30 CEST 2016
 * 
 */
public class AutorisationDAOImpl
    extends AbstractGenericDAO<Autorisation, Long>
    implements AutorisationDAO
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @PersistenceManager(unitName = "gepac")
    protected EntityManager em;

    public AutorisationDAOImpl() {
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * Methode permettant de retourner la classe de l'entite
     * 
     */
    @Override
    public Class<Autorisation> getManagedEntityClass() {
        return (Autorisation.class);
    }

}
