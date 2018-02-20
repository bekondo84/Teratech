
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.CiviliteDAOLocal;
import com.teratech.stock.dao.ifaces.base.CiviliteDAORemote;
import com.teratech.stock.model.base.Civilite;

@Stateless(mappedName = "CiviliteDAO")
public class CiviliteDAOImpl
    extends AbstractGenericDAO<Civilite, Long>
    implements CiviliteDAOLocal, CiviliteDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public CiviliteDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Civilite> getManagedEntityClass() {
        return (Civilite.class);
    }

}
