
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.JournalSaisieManagerLocal;
import com.basaccount.core.ifaces.operations.JournalSaisieManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "JournalSaisieManager")
public class JournalSaisieManagerImpl
    extends AbstractGenericManager<JournalSaisie, Long>
    implements JournalSaisieManagerLocal, JournalSaisieManagerRemote
{

    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal dao;
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;    
    
    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    

    public JournalSaisieManagerImpl() {
    }

    @Override
    public GenericDAO<JournalSaisie, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<JournalSaisie> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.
        List<JournalSaisie> datas = super.filter(predicats, orders, properties, firstResult, maxResult); 
        List<JournalSaisie> result = new ArrayList<JournalSaisie>();
        for(JournalSaisie data:datas){
            result.add(new JournalSaisie(data));
        }
        return result;
    }

    @Override
    public List<JournalSaisie> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<JournalSaisie> datas =  super.findAll(); 
        List<JournalSaisie> result = new ArrayList<JournalSaisie>();
        for(JournalSaisie data:datas){
            result.add(new JournalSaisie(data));
        }
        return result;
    }

   
    @Override
    public JournalSaisie find(String propertyName, Long id) {
        JournalSaisie data = super.find(propertyName, id); //To change body of generated methods, choose Tools | Templates.
        JournalSaisie result = new JournalSaisie(data);
        for(EcritureComptable ecrit:data.getOperations()){
            EcritureComptable ecriture = new EcritureComptable(ecrit);            
            result.getOperations().add(ecriture);
        }
        return result;
    }

    @Override
    public void processBeforeUpdate(JournalSaisie entity) {
        //Map contenant les ecritures 
//        Map<Long , EcritureComptable> map = new HashMap<Long,EcritureComptable>();
//        if(entity.getOperations()!=null){
//            for(EcritureComptable ecriture:entity.getOperations()){
//                //Traitement des comptes analytiques
//                ecritureAnalytiqueGenerator(ecriture);
//                //Generation des ecritures tiers
//                ecritureTierGenerator(ecriture);
//                ecriture.setExercice(entity.getExercice());
//                ecriture.setJournal(entity.getJournal());
//                ecriture.setJournaldesaisie(entity);
//                if(ecriture.getId()>0){
//                    //Sauvegarde dans le map
//                    map.put(ecriture.getId(), ecriture);
//                    ecrituredao.update(ecriture.getId(), ecriture);
//                }else{
//                    if(ecriture.getCredit()!=null){
//                        entity.credit(ecriture.getCredit());
//                    }
//                    if(ecriture.getDebit()!=null){
//                        entity.debit(ecriture.getDebit());
//                    }
//                    ecrituredao.save(ecriture);
//                }
//            }//end for(EcritureComptable ecriture:entity.getOperations())
//        }//end if(entity.getOperations()!=null)
//        //Suppression des ecritures surprime
//        JournalSaisie old = dao.findByPrimaryKey("id",entity.getId());
////        entity.setJournal(old.getJournal());
//        if(old.getOperations()!=null){
//            for(EcritureComptable ecriture:old.getOperations()){
//                if(!map.containsKey(ecriture.getId())){
//                    if(ecriture.getCredit()!=null){
//                        entity.credit(ecriture.getCredit().negate());
//                    }
//                    if(ecriture.getDebit()!=null){
//                        entity.debit(ecriture.getDebit().negate());
//                    }
//                    ecrituredao.delete(ecriture.getId());
//                }//end if(!map.containsKey(ecriture.getId()))
//            }//end for(EcritureComptable ecriture:old.getOperations())
//        }//end if(old.getOperations()!=null)
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JournalSaisie update(Long id, JournalSaisie entity) {
        return new JournalSaisie(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public JournalSaisie delete(Long id) {
         //To change body of generated methods, choose Tools | Templates.
        return new JournalSaisie();
    }   
    
     /**
     * Genere les ecritures analytiques
     * @param entity 
     */
    private void ecritureAnalytiqueGenerator(EcritureComptable entity){
        Compte compte = comptedao.findByPrimaryKey("id", entity.getCompte().getId());
        if(compte.getAnalytiques()!=null&&!compte.getAnalytiques().isEmpty()){
            for(SectionAnalytique sec:compte.getAnalytiques()){
                double debit = 0;
                double credit = 0;
                if(sec.getType().equalsIgnoreCase("0")){//pourcentage
                    debit = (entity.getDebit()!=null ? entity.getDebit().doubleValue():0)*sec.getQuantite()/100;
                    credit = (entity.getCredit()!=null ? entity.getCredit().doubleValue():0)*sec.getQuantite()/100;
                }else if(sec.getType().equalsIgnoreCase("1")){
                    debit = (entity.getDebit()!=null ? entity.getDebit().doubleValue():0)/entity.getAnalytiques().size();
                    credit = (entity.getCredit()!=null ? entity.getCredit().doubleValue():0)/entity.getAnalytiques().size();
                }else{
                    debit = (entity.getDebit()!=null ? sec.getQuantite():0);
                    credit = (entity.getCredit()!=null ? sec.getQuantite():0);
                }
                EcritureAnalytique ecriture = new EcritureAnalytique(entity, sec.getCompte(), BigDecimal.valueOf(debit), BigDecimal.valueOf(credit));
                entity.getAnalytiques().add(ecriture);
            }//end for(SectionAnalytique sec:compte.getAnalytiques())
        }//end if(compte.getAnalytiques()!=null&&!compte.getAnalytiques().isEmpty())
    }//end private void ecritureAnalytiqueGenerator

    /**
     * 
     * @param entity 
     */
    private void ecritureTierGenerator(EcritureComptable entity){
       if(entity.getTier()!=null){ 
            EcritureTier ecriture = new EcritureTier(entity);
            entity.setEcrituretier(ecriture);
       }//end if(entity.getTier()!=null)
    }
}
