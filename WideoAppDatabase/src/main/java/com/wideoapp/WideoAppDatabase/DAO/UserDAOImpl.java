package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) { entityManager = theEntityManager;}

    @Override
    public List<User> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> theQuery =
                currentSession.createQuery("from User", User.class);

        List<User> users = theQuery.getResultList();

        return users;
    }
}
