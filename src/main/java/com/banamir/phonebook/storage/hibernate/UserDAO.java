package com.banamir.phonebook.storage.hibernate;

import com.banamir.phonebook.manager.UserManager;
import com.banamir.phonebook.model.PhonebookUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


public class UserDAO implements UserManager {

    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public PhonebookUser getUserByUsername(String username) {

        Session session =  sessionFactory.getCurrentSession();

        String queryStr = "FROM PhonebookUser AS u WHERE u.username = :p_username";

        Query query = session.createQuery(queryStr);
        query.setParameter("p_username",username);

        List result = query.list();

        return (result.isEmpty())? null : (PhonebookUser) result.get(0);
    }

    @Transactional
    @Override
    public PhonebookUser addUser(PhonebookUser user) {

        Session session =  sessionFactory.getCurrentSession();

        Serializable id = session.save(user);

        return (PhonebookUser) session.get(PhonebookUser.class, id);
    }

    @Transactional
    @Override
    public PhonebookUser updateUser(PhonebookUser user) {

        Session session =  sessionFactory.getCurrentSession();

        session.update(user);

        return (PhonebookUser) session.get(PhonebookUser.class, user.getId());
    }

    @Transactional
    @Override
    public Long deleteUser(PhonebookUser user) {

        Session session =  sessionFactory.getCurrentSession();

        session.delete(user);

        return user.getId();
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
