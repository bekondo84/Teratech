
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.CompteManagerLocal;
import com.basaccount.core.ifaces.comptabilite.CompteManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.comptabilite.Taxe;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "CompteManager")
public class CompteManagerImpl
    extends AbstractGenericManager<Compte, Long>
    implements CompteManagerLocal, CompteManagerRemote
{

    @EJB(name = "CompteDAO")
    protected CompteDAOLocal dao;

    public CompteManagerImpl() {
    }

    @Override
    public GenericDAO<Compte, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<Compte> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Compte> datas =  super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Compte> result = new ArrayList<Compte>();
        for(Compte data:datas){
            result.add(new Compte(data));
        }
        return result;
    }

    @Override
    public List<Compte> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<Compte> datas =  super.findAll(); 
        List<Compte> result = new ArrayList<Compte>();
        for(Compte data:datas){
            result.add(new Compte(data));
        }
        return result;
    }

    @Override
    public Compte find(String propertyName, Long entityID) {
        Compte data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        Compte result = new Compte(data);
//        if(data.getTaxe()!=null){
//            result.setTaxe(new Taxe(data.getTaxe()));
//        }
//        for(SectionAnalytique cpte:data.getAnalytiques()){
//            result.getAnalytiques().add(new SectionAnalytique(cpte));
//        }
        return result;
    }
    
    

}
