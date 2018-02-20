
package com.megatim.security.dao.impl.Utilisateur;

import javax.persistence.EntityManager;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.megatim.common.annotations.PersistenceManager;
import com.megatim.security.dao.ifaces.Utilisateur.UtilisateurDAO;
import com.megatim.security.model.Utilisateur;


/**
 * Classe d'implementation de la DAO

 * @since Sun Sep 18 21:53:30 CEST 2016
 * 
 */
public class UtilisateurDAOImpl
    extends AbstractGenericDAO<Utilisateur, String>
    implements UtilisateurDAO
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @PersistenceManager(unitName = "gepac")
    protected EntityManager em;

    public UtilisateurDAOImpl() {
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
    public Class<Utilisateur> getManagedEntityClass() {
        return (Utilisateur.class);
    }

}
