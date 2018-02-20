
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.DeviseDAOLocal;
import com.teratech.stock.dao.ifaces.base.DeviseDAORemote;
import com.teratech.stock.model.base.Devise;

@Stateless(mappedName = "DeviseDAO")
public class DeviseDAOImpl
    extends AbstractGenericDAO<Devise, Long>
    implements DeviseDAOLocal, DeviseDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public DeviseDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Devise> getManagedEntityClass() {
        return (Devise.class);
    }

}
