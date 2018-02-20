
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerLocal;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.JournalComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.commons.DateHelper;
import java.util.Date;
import java.util.List;

@TransactionAttribute
@Stateless(mappedName = "ExerciceComptableManager")
public class ExerciceComptableManagerImpl
    extends AbstractGenericManager<ExerciceComptable, Long>
    implements ExerciceComptableManagerLocal, ExerciceComptableManagerRemote
{

    @EJB(name = "ExerciceComptableDAO")
    protected ExerciceComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;
    
    @EJB(name = "JournalComptableDAO")
    protected JournalComptableDAOLocal journaldao;

    public ExerciceComptableManagerImpl() {
    }

    @Override
    public GenericDAO<ExerciceComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public void processAfterSave(ExerciceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.
        //L'exercice comptable tient sur 12 mois
        entity = dao.findByPrimaryKey("code", entity.getCode());
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        List<JournalComptable> journaux = journaldao.filter(container.getPredicats(), null, null, 0 , -1);
        String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
        if(journaux!=null){
            for(JournalComptable journal : journaux){
                Date begin = entity.getDebut();
                while(DateHelper.convertToString(begin, "yyyy-MM-dd")
                        .compareTo(DateHelper.convertToString(entity.getFin(), "yyyy-MM-dd"))<=0){
                    String code = shortMonths[begin.getMonth()]+"."+DateHelper.convertToString(begin, "yy");
                    JournalSaisie saisie = new JournalSaisie(code, entity, DateHelper.getFirstDayOfMonth(begin)
                            , DateHelper.getLastDayOfMonth(begin));
                    saisie.setJournal(journal);
                    journalsaisiedao.save(saisie);
                    begin = DateHelper.nextMonth(begin);
                }//end while(DateHelper.convertToString(begin, "yyyy-MM-dd")
            }//end for(JournalComptable journal : journaux)
        }//end if(journaux!=null)o
        if(entity.isOuvert()){
            List<ExerciceComptable> datas = dao.filter(RestrictionsContainer.newInstance().getPredicats(), null, null, 0, -1);
            if(datas!=null){
                for(ExerciceComptable ex:datas){
                    if(ex.isOuvert()){
                        ex.setOuvert(false);
                        dao.update(ex.getId(), ex);
                    }
                }
            }//end if(datas!=null){
        }//end if(entity.isOuvert()){
    }

    @Override
    public void processBeforeUpdate(ExerciceComptable entity) {
        if(entity.isOuvert()){
            List<ExerciceComptable> datas = dao.filter(RestrictionsContainer.newInstance().getPredicats(), null, null, 0, -1);
            if(datas!=null){
                for(ExerciceComptable ex:datas){
                    if(ex.isOuvert()){
                        ex.setOuvert(false);
                        dao.update(ex.getId(), ex);
                    }
                }
            }//end if(datas!=null){
        }//end if(entity.isOuvert()){
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
