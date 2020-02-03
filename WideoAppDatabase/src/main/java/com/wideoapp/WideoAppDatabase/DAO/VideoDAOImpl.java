package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoDAOImpl implements VideoDAO{

    private EntityManager entityManager;

    public VideoDAOImpl(EntityManager theEntityManager) { entityManager = theEntityManager;}

    @Override
    public List<Video> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video", Video.class);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }
}
