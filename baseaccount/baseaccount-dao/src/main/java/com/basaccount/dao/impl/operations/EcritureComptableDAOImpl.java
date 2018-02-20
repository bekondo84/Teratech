
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAORemote;
import com.basaccount.model.operations.EcritureComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EcritureComptableDAO")
public class EcritureComptableDAOImpl
    extends AbstractGenericDAO<EcritureComptable, Long>
    implements EcritureComptableDAOLocal, EcritureComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcritureComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcritureComptable> getManagedEntityClass() {
        return (EcritureComptable.class);
    }

}
