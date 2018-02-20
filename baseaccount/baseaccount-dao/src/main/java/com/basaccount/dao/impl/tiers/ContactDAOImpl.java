
package com.basaccount.dao.impl.tiers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.tiers.ContactDAOLocal;
import com.basaccount.dao.ifaces.tiers.ContactDAORemote;
import com.basaccount.model.tiers.Contact;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ContactDAO")
public class ContactDAOImpl
    extends AbstractGenericDAO<Contact, Long>
    implements ContactDAOLocal, ContactDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ContactDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Contact> getManagedEntityClass() {
        return (Contact.class);
    }

}
