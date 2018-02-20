
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.CompteDAORemote;
import com.basaccount.model.comptabilite.Compte;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "CompteDAO")
public class CompteDAOImpl
    extends AbstractGenericDAO<Compte, Long>
    implements CompteDAOLocal, CompteDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public CompteDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Compte> getManagedEntityClass() {
        return (Compte.class);
    }

}
