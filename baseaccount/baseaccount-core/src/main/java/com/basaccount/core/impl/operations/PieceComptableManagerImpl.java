
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.PieceComptableManagerLocal;
import com.basaccount.core.ifaces.operations.PieceComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.dao.ifaces.operations.PieceComptableDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.operations.JournalSaisie;
import com.basaccount.model.operations.PieceComptable;
import com.basaccount.model.tiers.Tier;
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
import javax.ws.rs.core.Response;

@TransactionAttribute
@Stateless(mappedName = "PieceComptableManager")
public class PieceComptableManagerImpl
    extends AbstractGenericManager<PieceComptable, Long>
    implements PieceComptableManagerLocal, PieceComptableManagerRemote
{

    @EJB(name = "PieceComptableDAO")
    protected PieceComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;
    
    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;    
    
    
    public PieceComptableManagerImpl() {
    }

    @Override
    public GenericDAO<PieceComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<PieceComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<PieceComptable> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<PieceComptable> results = new ArrayList<PieceComptable>();
        for(PieceComptable piece: datas){
            results.add(new PieceComptable(piece));
        }
        return results;
    }

    @Override
    public List<PieceComptable> findAll() {
        List<PieceComptable> datas =  super.findAll(); //To change body of generated methods, choose Tools | Templates.
        List<PieceComptable> results = new ArrayList<PieceComptable>();
        for(PieceComptable piece: datas){
            results.add(new PieceComptable(piece));
        }
        return results;
    }

    @Override
    public PieceComptable find(String propertyName, Long entityID) {
        //To change body of generated methods, choose Tools | Templates.
        PieceComptable piece = super.find(propertyName, entityID); 
        if(piece.getEcritures()!=null){
            for(EcritureComptable ecri:piece.getEcritures()){
                if(ecri.getAnalytiques()!=null){
                    ecri.getAnalytiques().size();
                }
                if(ecri.getTier()!=null){
                    ecri.setTier(new Tier(ecri.getTier()));
                }
                ecri.setCompte(new Compte(ecri.getCompte()));
                ecri.setJournaldesaisie(new JournalSaisie(ecri.getJournaldesaisie()));
                if(ecri.getEcrituretier()!=null){
                    ecri.getEcrituretier().setCompte(new Tier(ecri.getEcrituretier().getCompte()));
                }
            }//end for(EcritureComptable ecri:piece.getEcritures())
        }//end if(piece.getEcritures()!=null)
        return piece;
    }

    @Override
    public void processBeforeUpdate(PieceComptable entity) {
        if(entity.getEcritures()!=null){
            BigDecimal debit = BigDecimal.ZERO;
            BigDecimal credit = BigDecimal.ZERO;
            String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
            for(EcritureComptable ecrit:entity.getEcritures()){
                debit = debit.add(ecrit.getDebit());
                credit = credit.add(ecrit.getCredit()); 
                if(ecrit.getId()<0){
                    //Generateur d'ecriture analytique
                    ecritureAnalytiqueGenerator(ecrit);
                    //Generation des ecritures tiers
                    ecritureTierGenerator(ecrit);
                    String code = shortMonths[ecrit.getDateEcriture().getMonth()]+"."+DateHelper.convertToString(ecrit.getDateEcriture(), "yy");
                    RestrictionsContainer container = RestrictionsContainer.newInstance();
                    container.addEq("code", code);
                    container.addEq("journal", entity.getJournal());
                    List<JournalSaisie> saisies = journalsaisiedao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
                    if(saisies.size()>0){
                        JournalSaisie saisie = saisies.get(0);
                        saisie.credit(entity.getCredit());
                        saisie.debit(entity.getDebit());
                        ecrit.setExercice(saisie.getExercice());
                        ecrit.setJournaldesaisie(new JournalSaisie(saisie));  
//                        ecrituredao.save(ecrit);
                        journalsaisiedao.update(saisie.getId(), saisie);
                        
                    }else {
                        RuntimeException excep = new RuntimeException("Impossible de trouver le journal de saisie ");
                        throw new WebApplicationException();
                    }
                }
                
            }
            entity.setCredit(credit);
            entity.setDebit(debit);
        }//end if(entity.getEcritures()!=null)
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void processBeforeSave(PieceComptable entity) {
//        entity = dao.findByPrimaryKey("code", entity.getCode());
        if(entity.getEcritures()!=null){
            BigDecimal debit = BigDecimal.ZERO;
            BigDecimal credit = BigDecimal.ZERO;
            String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
            for(EcritureComptable ecrit:entity.getEcritures()){
                    //Generateur d'ecriture analytique
                    ecritureAnalytiqueGenerator(ecrit);
                    //Generation des ecritures tiers
                    ecritureTierGenerator(ecrit);
                    ecrit.setJournal(entity.getJournal());
                    if(ecrit.getDebit()!=null){
                        debit = debit.add(ecrit.getDebit());
                    }
                    if(ecrit.getCredit()!=null){
                        credit = credit.add(ecrit.getCredit());
                    }  
                    String code = shortMonths[ecrit.getDateEcriture().getMonth()]+"."+DateHelper.convertToString(ecrit.getDateEcriture(), "yy");
                    RestrictionsContainer container = RestrictionsContainer.newInstance();
                    container.addEq("code", code);
                    container.addEq("journal", entity.getJournal());
                    List<JournalSaisie> saisies = journalsaisiedao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
                    if(saisies.size()>0){
                        JournalSaisie saisie = saisies.get(0);
                        entity.setExercice(saisie.getExercice());
                        ecrit.setExercice(saisie.getExercice());
                        saisie.credit(ecrit.getCredit());
                        saisie.debit(ecrit.getDebit());
                        ecrit.setJournaldesaisie(new JournalSaisie(saisie));                        
//                        ecrituredao.save(ecrit);
                        journalsaisiedao.update(saisie.getId(), saisie);
                        
                    }else {
                        RuntimeException excep = new RuntimeException("Impossible de trouver le journal de saisie ");
                        throw new WebApplicationException(excep);
                    }
            }
            entity.setCredit(credit);
            entity.setDebit(debit);
            //Traitement des journaux de saisie            
        }else{//end if(entity.getEcritures()!=null)
            RuntimeException excep = new RuntimeException("Veuillez saisir au moins une ecriture");
            throw new WebApplicationException(excep, Response.Status.NOT_MODIFIED);
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public PieceComptable delete(Long id) {
        PieceComptable piece = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        PieceComptable entity = new PieceComptable(piece);
        if(entity.getEcritures()!=null){
            BigDecimal debit = BigDecimal.ZERO;
            BigDecimal credit = BigDecimal.ZERO;
            String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
            for(EcritureComptable ecrit:entity.getEcritures()){
                    ecrit.setJournal(entity.getJournal());
                    String code = shortMonths[ecrit.getDateEcriture().getMonth()]+"."+DateHelper.convertToString(ecrit.getDateEcriture(), "yy");
                    RestrictionsContainer container = RestrictionsContainer.newInstance();
                    container.addEq("code", code);
                    container.addEq("journal", entity.getJournal());
                    List<JournalSaisie> saisies = journalsaisiedao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
                    if(saisies.size()>0){
                        JournalSaisie saisie = saisies.get(0);
                        if(ecrit.getCredit()!=null){
                            saisie.credit(ecrit.getCredit().negate());
                        }
                        if(ecrit.getDebit()!=null){
                            saisie.debit(ecrit.getDebit().negate());
                        }
                        ecrit.setJournaldesaisie(new JournalSaisie(saisie));  
//                        ecrituredao.delete(ecrit.getId());
                        journalsaisiedao.update(saisie.getId(), saisie);
                    }else {
                        RuntimeException excep = new RuntimeException("Impossible de trouver le journal de saisie ");
                        throw new WebApplicationException(excep);
                    }//end if(saisies.size()>0)
            }//end for       
      }
         return entity;
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
