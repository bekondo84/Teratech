
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.UniteGestionDAOLocal;
import com.teratech.stock.dao.ifaces.base.UniteGestionDAORemote;
import com.teratech.stock.model.base.UniteGestion;

@Stateless(mappedName = "UniteGestionDAO")
public class UniteGestionDAOImpl
    extends AbstractGenericDAO<UniteGestion, Long>
    implements UniteGestionDAOLocal, UniteGestionDAORemote
{

    @PersistenceContext(unitName = "teratech")
    protected EntityManager em;

    public UniteGestionDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<UniteGestion> getManagedEntityClass() {
        return (UniteGestion.class);
    }

}
