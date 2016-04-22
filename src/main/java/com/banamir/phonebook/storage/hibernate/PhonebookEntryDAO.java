package com.banamir.phonebook.storage.hibernate;


import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PhonebookEntryDAO implements PhonebookManager {

    public final String filterQuery =
             " AND ( e.firstName LIKE :filter"
           + " OR    e.middleName LIKE :filter"
           + " OR    e.lastName LIKE :filter"
           + " OR    e.mobilePhone LIKE :filter"
           + " OR    e.homePhone LIKE :filter)";
            ;

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Collection<PhonebookEntry> entries(PhonebookUser user, String filter) {

        Session session =  sessionFactory.getCurrentSession();

        String queryStr = "FROM PhonebookEntry AS e WHERE e.userId = :p_user_id";
        if(filter != null) {
            queryStr += filterQuery;
        }

        Query query = session.createQuery(queryStr);
        query.setParameter("p_user_id",user.getId());
        if(filter != null) {
            query.setParameter(":filter", "%" + filter + "%");
        }

        List result = query.list();

        return (Collection<PhonebookEntry>) result;
    }

    @Override
    @Transactional
    public Collection<PhonebookEntry> entries(PhonebookUser user) {

        return entries(user, null);
    }

    @Override
    public PhonebookEntry getEntry(Long id) {

        Session session =  sessionFactory.getCurrentSession();

        return (PhonebookEntry) session.get(PhonebookEntry.class, id);
    }

    @Override
    public PhonebookEntry addEntry(PhonebookEntry entry) {

        Session session =  sessionFactory.getCurrentSession();

        Serializable id = session.save(entry);

        return (PhonebookEntry) session.get(PhonebookEntry.class, id);
    }

    @Override
    public PhonebookEntry updateEntry(PhonebookEntry entry) {

        Session session =  sessionFactory.getCurrentSession();

        session.update(entry);

        return (PhonebookEntry) session.get(PhonebookEntry.class, entry.getId());
    }

    @Override
    public Long deleteEntry(PhonebookEntry entry) {

        Session session =  sessionFactory.getCurrentSession();

        session.delete(entry);

        return entry.getId();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
