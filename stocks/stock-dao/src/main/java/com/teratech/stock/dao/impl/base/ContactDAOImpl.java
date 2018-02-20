
package com.teratech.stock.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.stock.dao.ifaces.base.ContactDAOLocal;
import com.teratech.stock.dao.ifaces.base.ContactDAORemote;
import com.teratech.stock.model.base.Contact;

@Stateless(mappedName = "ContactDAO")
public class ContactDAOImpl
    extends AbstractGenericDAO<Contact, Long>
    implements ContactDAOLocal, ContactDAORemote
{

    @PersistenceContext(unitName = "teratech")
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
