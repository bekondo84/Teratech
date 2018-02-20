
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.ClotureJournalDAOLocal;
import com.basaccount.dao.ifaces.operations.ClotureJournalDAORemote;
import com.basaccount.model.operations.ClotureJournal;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ClotureJournalDAO")
public class ClotureJournalDAOImpl
    extends AbstractGenericDAO<ClotureJournal, Long>
    implements ClotureJournalDAOLocal, ClotureJournalDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ClotureJournalDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ClotureJournal> getManagedEntityClass() {
        return (ClotureJournal.class);
    }

}
