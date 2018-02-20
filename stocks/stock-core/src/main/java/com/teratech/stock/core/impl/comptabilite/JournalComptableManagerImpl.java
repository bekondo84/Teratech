
package com.teratech.stock.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.stock.core.ifaces.comptabilite.JournalComptableManagerLocal;
import com.teratech.stock.core.ifaces.comptabilite.JournalComptableManagerRemote;
import com.teratech.stock.dao.ifaces.comptabilite.JournalComptableDAOLocal;
import com.teratech.stock.model.comptabilite.JournalComptable;

@TransactionAttribute
@Stateless(mappedName = "JournalComptableManager")
public class JournalComptableManagerImpl
    extends AbstractGenericManager<JournalComptable, Long>
    implements JournalComptableManagerLocal, JournalComptableManagerRemote
{

    @EJB(name = "JournalComptableDAO")
    protected JournalComptableDAOLocal dao;

    public JournalComptableManagerImpl() {
    }

    @Override
    public GenericDAO<JournalComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }
    
    

}
