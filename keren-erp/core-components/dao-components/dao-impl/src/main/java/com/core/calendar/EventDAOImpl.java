
package com.core.calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EventDAO")
public class EventDAOImpl
    extends AbstractGenericDAO<Event, Long>
    implements EventDAOLocal, EventDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EventDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Event> getManagedEntityClass() {
        return (Event.class);
    }

}
