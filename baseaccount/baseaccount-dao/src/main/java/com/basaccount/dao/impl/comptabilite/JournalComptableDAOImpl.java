
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.JournalComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.JournalComptableDAORemote;
import com.basaccount.model.comptabilite.JournalComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "JournalComptableDAO")
public class JournalComptableDAOImpl
    extends AbstractGenericDAO<JournalComptable, Long>
    implements JournalComptableDAOLocal, JournalComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public JournalComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<JournalComptable> getManagedEntityClass() {
        return (JournalComptable.class);
    }

}
