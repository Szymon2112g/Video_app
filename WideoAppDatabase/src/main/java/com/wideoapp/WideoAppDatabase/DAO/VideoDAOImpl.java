package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class VideoDAOImpl implements VideoDAO{

    private EntityManager entityManager;

    public VideoDAOImpl(EntityManager theEntityManager) { entityManager = theEntityManager;}

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Video> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by date DESC", Video.class);
        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public Video findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> videoQuery = currentSession.createQuery("from Video where id = :id ");
        videoQuery.setParameter("id", id);

        return videoQuery.getSingleResult();
    }

    @Override
    public void increaseDisplay(Video theVideo) {
        Session currentSession = entityManager.unwrap(Session.class);
        theVideo.setDisplay(theVideo.getDisplay() + 1);
        currentSession.merge(theVideo);
    }

    @Override
    public List<Video> findByTableColumn(String category) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery;

        switch (category) {
            case "most-views":
                theQuery = currentSession.createQuery("from Video order by display DESC",Video.class);
                break;
            case "most-likes":
                theQuery = currentSession.createQuery("from Video order by likes DESC",Video.class);
                break;
            case "most-dislikes":
                theQuery = currentSession.createQuery("from Video order by dislikes DESC",Video.class);
                break;
            case "latest":
                theQuery = currentSession.createQuery("from Video order by date DESC",Video.class);
                break;
            default:
                theQuery = currentSession.createQuery("from Video order by display DESC",Video.class);
                break;
        }

        theQuery.setMaxResults(4);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }
}
