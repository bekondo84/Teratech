
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.ClotureJournalManagerLocal;
import com.basaccount.core.ifaces.operations.ClotureJournalManagerRemote;
import com.basaccount.dao.ifaces.operations.ClotureJournalDAOLocal;
import com.basaccount.model.operations.ClotureJournal;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "ClotureJournalManager")
public class ClotureJournalManagerImpl
    extends AbstractGenericManager<ClotureJournal, Long>
    implements ClotureJournalManagerLocal, ClotureJournalManagerRemote
{

    @EJB(name = "ClotureJournalDAO")
    protected ClotureJournalDAOLocal dao;

    public ClotureJournalManagerImpl() {
    }

    @Override
    public GenericDAO<ClotureJournal, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public ClotureJournal save(ClotureJournal entity) {
         //To change body of generated methods, choose Tools | Templates.
        System.out.println(ClotureJournalManagerImpl.class.getName()+" =============== Vous avez cliquez pour cloturer : "+entity);
        return new ClotureJournal();
    }
    
    

}
