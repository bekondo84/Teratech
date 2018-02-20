
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAORemote;
import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "JournalSaisieDAO")
public class JournalSaisieDAOImpl
    extends AbstractGenericDAO<JournalSaisie, Long>
    implements JournalSaisieDAOLocal, JournalSaisieDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public JournalSaisieDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<JournalSaisie> getManagedEntityClass() {
        return (JournalSaisie.class);
    }

}
