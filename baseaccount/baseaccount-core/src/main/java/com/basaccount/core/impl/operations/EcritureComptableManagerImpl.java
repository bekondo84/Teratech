
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureComptableManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.operations.JournalSaisie;
import com.basaccount.model.search.EcritureSearch;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.commons.DateHelper;
import com.megatim.common.annotations.OrderType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.WebApplicationException;

@TransactionAttribute
@Stateless(mappedName = "EcritureComptableManager")
public class EcritureComptableManagerImpl
    extends AbstractGenericManager<EcritureComptable, Long>
    implements EcritureComptableManagerLocal, EcritureComptableManagerRemote
{

    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;

    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    
    public EcritureComptableManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<EcritureComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<EcritureComptable> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<EcritureComptable> result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){
            result.add(new EcritureComptable(ecrit));
        }
        return result;
    }

    @Override
    public List<EcritureComptable> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<EcritureComptable> datas =  super.findAll(); 
        List<EcritureComptable> result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){
            result.add(new EcritureComptable(ecrit));
        }
        return result;
    }

    @Override
    public EcritureComptable find(String propertyName, Long entityID) {
        //To change body of generated methods, choose Tools | Templates.
        EcritureComptable data = super.find(propertyName, entityID); 
        return new EcritureComptable(data);
    }

    @Override
    public EcritureComptable delete(Long id) {
        EcritureComptable ecriture = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
        String code = shortMonths[ecriture.getDateEcriture().getMonth()]+"."+DateHelper.convertToString(ecriture.getDateEcriture(), "yy");
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("code", code);
        container.addEq("journal", ecriture.getJournal());
        List<JournalSaisie> saisies = journalsaisiedao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        if(saisies.size()>0){
            JournalSaisie saisie = saisies.get(0);
            if(ecriture.getCredit()!=null){
                saisie.credit(ecriture.getCredit().negate());
            }
            if(ecriture.getDebit()!=null){
                saisie.debit(ecriture.getDebit().negate());
            }
            ecriture.setJournaldesaisie(new JournalSaisie(saisie));
            journalsaisiedao.update(saisie.getId(), saisie);
        }else {
            RuntimeException excep = new RuntimeException("Impossible de trouver le journal de saisie ");
            throw new WebApplicationException(excep);
        }
        return new EcritureComptable(ecriture);
    }

    @Override
    public void processBeforeSave(EcritureComptable entity) {
        //Generation des ecritures analytiques
        ecritureAnalytiqueGenerator(entity);
        //Generation des ecriture tier
        ecritureTierGenerator(entity);
         //Traitement des journal de saisie
        String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
        String code = shortMonths[entity.getDateEcriture().getMonth()]+"."+DateHelper.convertToString(entity.getDateEcriture(), "yy");
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("code", code);
        container.addEq("journal", entity.getJournal());
        List<JournalSaisie> saisies = journalsaisiedao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        if(saisies.size()>0){
            JournalSaisie saisie = saisies.get(0);
            saisie.credit(entity.getCredit());
            saisie.debit(entity.getDebit());
            entity.setExercice(saisie.getExercice());
            entity.setJournaldesaisie(new JournalSaisie(saisie));
            journalsaisiedao.update(saisie.getId(), saisie);
        }else {
            RuntimeException excep = new RuntimeException("Impossible de trouver le journal de saisie ");
            throw new WebApplicationException(excep);
        }
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

    @Override
    public List<EcritureComptable> somethings(EcritureSearch critere) {
         //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        if(critere!=null){
            if(critere.getSource()!=null){
                container.addGe("compte", critere.getSource());
            }
            if(critere.getCible()!=null){
                container.addLe("compte", critere.getCible());
            }
            if(critere.getDebut()!=null){
                container.addGe("dateEcriture", critere.getDebut());
            }
            if(critere.getFin()!=null){
                container.addLe("dateEcriture", critere.getFin());
            }
        }
        List<EcritureComptable> datas = dao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        List<EcritureComptable>  result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){            
            EcritureComptable ecriture = new EcritureComptable(ecrit);
            result.add(ecriture);
        }
        return result;
    }
}
