package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public String addUser(User user) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession
                .createNativeQuery("insert into user(`id`,`first_name`,`last_name`,`email`,`password`)" +
                        " values (0,:fName,:lName,:email,:password)");

        theQuery.setParameter("fName",user.getFirstName());
        theQuery.setParameter("lName",user.getLastName());
        theQuery.setParameter("email",user.getEmail());
        theQuery.setParameter("password",user.getPassword());

        int response = theQuery.executeUpdate();

        return "correct";
    }
}
