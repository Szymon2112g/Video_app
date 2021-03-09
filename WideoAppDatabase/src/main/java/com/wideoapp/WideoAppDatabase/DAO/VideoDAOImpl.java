package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VideoDAOImpl implements VideoDAO{

    private EntityManager entityManager;

    public VideoDAOImpl(EntityManager theEntityManager) { entityManager = theEntityManager;}

    @Override
    public List<Video> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by date DESC", Video.class);
        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public Optional<Video> findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> videoQuery = currentSession.createQuery("from Video where id = :id ", Video.class);
        videoQuery.setParameter("id", id);

        return Optional.of(videoQuery.getSingleResult());
    }

    @Override
    public void increaseDisplay(Video video) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> videoQuery = currentSession.createQuery("update Video v set v.display = :keydisplay where v.id = :keyid");
        videoQuery.setParameter("keydisplay", video.getDisplay() + 1);
        videoQuery.setParameter("keyid", video.getId());

        videoQuery.executeUpdate();
    }

    @Override
    public List<Video> findTheMostDisplayVideo() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by display DESC",Video.class);

        theQuery.setMaxResults(4);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public List<Video> findTheMostLikesVideo() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by likes DESC",Video.class);

        theQuery.setMaxResults(4);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public List<Video> findTheMostDislikesVideo() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by dislikes DESC",Video.class);

        theQuery.setMaxResults(4);

        List<Video> videoList = theQuery.getResultList();

        return videoList;
    }

    @Override
    public List<Video> findLatestVideo() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Video> theQuery = currentSession.createQuery("from Video order by date DESC",Video.class);

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
