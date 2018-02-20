
package com.megatim.security.dao.impl.Profil;

import javax.persistence.EntityManager;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.megatim.common.annotations.PersistenceManager;
import com.megatim.security.dao.ifaces.Profil.ProfilDAO;
import com.megatim.security.model.Profil;


/**
 * Classe d'implementation de la DAO

 * @since Sun Sep 18 21:53:30 CEST 2016
 * 
 */
public class ProfilDAOImpl
    extends AbstractGenericDAO<Profil, String>
    implements ProfilDAO
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @PersistenceManager(unitName = "gepac")
    protected EntityManager em;

    public ProfilDAOImpl() {
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
    public Class<Profil> getManagedEntityClass() {
        return (Profil.class);
    }

}
