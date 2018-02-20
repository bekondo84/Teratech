
package com.basaccount.dao.impl.banques;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.banques.CompteBancaireDAOLocal;
import com.basaccount.dao.ifaces.banques.CompteBancaireDAORemote;
import com.basaccount.model.banques.CompteBancaire;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "CompteBancaireDAO")
public class CompteBancaireDAOImpl
    extends AbstractGenericDAO<CompteBancaire, Long>
    implements CompteBancaireDAOLocal, CompteBancaireDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public CompteBancaireDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<CompteBancaire> getManagedEntityClass() {
        return (CompteBancaire.class);
    }

}
