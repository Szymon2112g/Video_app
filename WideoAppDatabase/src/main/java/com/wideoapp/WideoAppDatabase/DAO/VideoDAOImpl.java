package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Video> findAllOrderByDateDesc() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Video> theQuery = currentSession.createQuery("from Video order by date DESC", Video.class);
        theQuery.setMaxResults(100);
        List<Video> videoList = theQuery.getResultList();
        return videoList;
    }

    @Override
    public List<String> findTipsByKey(String key) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video where title LIKE :key", Video.class);
        theQuery.setParameter("key","%" + key + "%");
        theQuery.setMaxResults(5);
        List<Video> videoList = theQuery.getResultList();


        List<String> tipsList = new ArrayList<>();

        for(Video video: videoList) {
            tipsList.add(video.getTitle());
        }

        tipsList = tipsList.stream().distinct().collect(Collectors.toList());

        return tipsList;
    }

    @Override
    public List<Video> findVideoByKey(String key) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video where title LIKE :keyfirst or description like :keyseconds", Video.class);
        theQuery.setParameter("keyfirst","%" + key + "%");
        theQuery.setParameter("keyseconds","%" + key + "%");
        theQuery.setMaxResults(50);
        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public List<Video> findVideoByUserId(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Video> theQuery = currentSession.createQuery("from Video where user_id = :id", Video.class);
        theQuery.setParameter("id", id);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }
}
