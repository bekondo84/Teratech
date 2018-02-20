
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.TaxeDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.TaxeDAORemote;
import com.basaccount.model.comptabilite.Taxe;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "TaxeDAO")
public class TaxeDAOImpl
    extends AbstractGenericDAO<Taxe, Long>
    implements TaxeDAOLocal, TaxeDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public TaxeDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Taxe> getManagedEntityClass() {
        return (Taxe.class);
    }

}
