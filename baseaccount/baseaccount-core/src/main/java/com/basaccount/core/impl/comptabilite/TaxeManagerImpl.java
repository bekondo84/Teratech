
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.TaxeManagerLocal;
import com.basaccount.core.ifaces.comptabilite.TaxeManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.TaxeDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.Taxe;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.core.referentiels.Societe;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "TaxeManager")
public class TaxeManagerImpl
    extends AbstractGenericManager<Taxe, Long>
    implements TaxeManagerLocal, TaxeManagerRemote
{

    @EJB(name = "TaxeDAO")
    protected TaxeDAOLocal dao;

    public TaxeManagerImpl() {
    }

    @Override
    public GenericDAO<Taxe, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<Taxe> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Taxe> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Taxe> result = new ArrayList<Taxe>();
        for(Taxe data:datas){
            result.add(new Taxe(data));
        }
        return result;
    }

    @Override
    public List<Taxe> findAll() {
        List<Taxe> datas =  super.findAll();      
        List<Taxe> result = new ArrayList<Taxe>();
        for(Taxe data:datas){
            result.add(new Taxe(data));
        }
        return result;
    }

    @Override
    public Taxe find(String propertyName, Long entityID) {
        Taxe data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        Taxe result = new Taxe(data);
        result.setSociete(data.getSociete());
        for(Compte cpte:data.getComptes()){
            result.getComptes().add(new Compte(cpte));
        }
        return result;
    }
    
    

}
