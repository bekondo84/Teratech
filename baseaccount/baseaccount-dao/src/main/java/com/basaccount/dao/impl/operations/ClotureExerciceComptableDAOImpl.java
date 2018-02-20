
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.ClotureExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.ClotureExerciceComptableDAORemote;
import com.basaccount.model.operations.ClotureExerciceComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ClotureExerciceComptableDAO")
public class ClotureExerciceComptableDAOImpl
    extends AbstractGenericDAO<ClotureExerciceComptable, Long>
    implements ClotureExerciceComptableDAOLocal, ClotureExerciceComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ClotureExerciceComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ClotureExerciceComptable> getManagedEntityClass() {
        return (ClotureExerciceComptable.class);
    }

}
