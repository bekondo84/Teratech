
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAORemote;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ExerciceComptableDAO")
public class ExerciceComptableDAOImpl
    extends AbstractGenericDAO<ExerciceComptable, Long>
    implements ExerciceComptableDAOLocal, ExerciceComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ExerciceComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ExerciceComptable> getManagedEntityClass() {
        return (ExerciceComptable.class);
    }

    @Override
    public ExerciceComptable getOpenExercice() {
         //To change body of generated methods, choose Tools | Templates.     
        try{
            ExerciceComptable exercice = (ExerciceComptable) em.createQuery("SELECT c FROM ExerciceComptable c WHERE c.ouvert ="+Boolean.TRUE).getSingleResult();

            return exercice;
        }catch(Exception ex){
            return null;
        }
    }

}
