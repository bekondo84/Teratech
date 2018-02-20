
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.SectionAnalytiqueManagerLocal;
import com.basaccount.core.ifaces.comptabilite.SectionAnalytiqueManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.SectionAnalytiqueDAOLocal;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "SectionAnalytiqueManager")
public class SectionAnalytiqueManagerImpl
    extends AbstractGenericManager<SectionAnalytique, Long>
    implements SectionAnalytiqueManagerLocal, SectionAnalytiqueManagerRemote
{

    @EJB(name = "SectionAnalytiqueDAO")
    protected SectionAnalytiqueDAOLocal dao;

    public SectionAnalytiqueManagerImpl() {
    }

    @Override
    public GenericDAO<SectionAnalytique, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
