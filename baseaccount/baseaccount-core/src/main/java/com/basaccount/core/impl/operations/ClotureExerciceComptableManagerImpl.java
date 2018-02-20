
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.ClotureExerciceComptableManagerLocal;
import com.basaccount.core.ifaces.operations.ClotureExerciceComptableManagerRemote;
import com.basaccount.dao.ifaces.operations.ClotureExerciceComptableDAOLocal;
import com.basaccount.model.operations.ClotureExerciceComptable;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "ClotureExerciceComptableManager")
public class ClotureExerciceComptableManagerImpl
    extends AbstractGenericManager<ClotureExerciceComptable, Long>
    implements ClotureExerciceComptableManagerLocal, ClotureExerciceComptableManagerRemote
{

    @EJB(name = "ClotureExerciceComptableDAO")
    protected ClotureExerciceComptableDAOLocal dao;

    public ClotureExerciceComptableManagerImpl() {
    }

    @Override
    public GenericDAO<ClotureExerciceComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public ClotureExerciceComptable save(ClotureExerciceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.
        System.out.println(ClotureExerciceComptableManagerImpl.class.getName()+" Vous voulez cloturer : "+entity);
        return new ClotureExerciceComptable();
    }

    
}
