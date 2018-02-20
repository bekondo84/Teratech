
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.EcritureTierDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureTierDAORemote;
import com.basaccount.model.operations.EcritureTier;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EcritureTierDAO")
public class EcritureTierDAOImpl
    extends AbstractGenericDAO<EcritureTier, Long>
    implements EcritureTierDAOLocal, EcritureTierDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcritureTierDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcritureTier> getManagedEntityClass() {
        return (EcritureTier.class);
    }

}
