
package com.teratech.stock.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import com.teratech.stock.core.ifaces.comptabilite.CompteAnalytiqueManagerLocal;
import com.teratech.stock.core.ifaces.comptabilite.CompteAnalytiqueManagerRemote;
import com.teratech.stock.dao.ifaces.comptabilite.CompteAnalytiqueDAOLocal;
import com.teratech.stock.model.comptabilite.CompteAnalytique;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "CompteAnalytiqueManager")
public class CompteAnalytiqueManagerImpl
    extends AbstractGenericManager<CompteAnalytique, Long>
    implements CompteAnalytiqueManagerLocal, CompteAnalytiqueManagerRemote
{

    @EJB(name = "CompteAnalytiqueDAO")
    protected CompteAnalytiqueDAOLocal dao;

    public CompteAnalytiqueManagerImpl() {
    }

    @Override
    public GenericDAO<CompteAnalytique, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<CompteAnalytique> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<CompteAnalytique> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<CompteAnalytique> result = new ArrayList<CompteAnalytique>();
        for(CompteAnalytique c:datas){
            result.add(new CompteAnalytique(c));
        }
        return result;
    }

    @Override
    public List<CompteAnalytique> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<CompteAnalytique> datas = super.findAll(); //To change body of generated methods, choose Tools | Templates.
        List<CompteAnalytique> result = new ArrayList<CompteAnalytique>();
        for(CompteAnalytique c:datas){
            result.add(new CompteAnalytique(c));
        }
        return result;
    }

    @Override
    public CompteAnalytique find(String propertyName, Long entityID) {
        CompteAnalytique data =  super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        return new CompteAnalytique(data);
    }

    @Override
    public CompteAnalytique delete(Long id) {
        CompteAnalytique data =  super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new CompteAnalytique(data);
    }
    
    

}
