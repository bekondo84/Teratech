
package com.basaccount.core.impl.tiers;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.tiers.ContactManagerLocal;
import com.basaccount.core.ifaces.tiers.ContactManagerRemote;
import com.basaccount.dao.ifaces.tiers.ContactDAOLocal;
import com.basaccount.model.tiers.Contact;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "ContactManager")
public class ContactManagerImpl
    extends AbstractGenericManager<Contact, Long>
    implements ContactManagerLocal, ContactManagerRemote
{

    @EJB(name = "ContactDAO")
    protected ContactDAOLocal dao;

    public ContactManagerImpl() {
    }

    @Override
    public GenericDAO<Contact, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
