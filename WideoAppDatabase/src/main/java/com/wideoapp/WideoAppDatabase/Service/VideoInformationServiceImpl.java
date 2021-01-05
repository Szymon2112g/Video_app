package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VideoInformationServiceImpl implements VideoInformationService {

    private VideoDAO videoDAO;
    private UserDAO userDAO;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoInformationServiceImpl(VideoDAO videoDAO, UserDAO userDAO) {
        this.videoDAO = videoDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public ExtendedVideoInformation findById(int id) {

        Video video = videoDAO.findById(id);
        User user = userDAO.findUserById(video.getUserId());

        ExtendedVideoInformation extendedVideoInformation =
                new ExtendedVideoInformation(video.getId(), video.getUrl(), video.getTitle(), video.getDescription(),
                        user.getFirstName(),user.getLastName(), user.getId(), video.getDisplay(),
                        video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes());

        return extendedVideoInformation;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findByCategory(String category) {
        List<Video> videoList = videoDAO.findByTableColumn(category);

        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> getVideosOnTime() {
        List<Video> videoList = videoDAO.findAllOrderByDateDesc();

        Collections.sort(videoList, (o1, o2) -> Integer.compare(o2.getDisplay(), o1.getDisplay()));

        for (int i = 10; i< videoList.size(); i++) {
            videoList.remove(i);
        }

        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<String> findTipsByKey(String key) {
        return videoDAO.findTipsByKey(key);
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findVideoByKey(String key) {

        List<Video> videoList = videoDAO.findVideoByKey(key);
        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findVideoByUserId(int userId) {

        List<Video> videoList = videoDAO.findVideoByUserId(userId);
        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    private List<ExtendedVideoInformation> swapVideoListToExtendedVideoInformationList(List<Video> videoList) {
        List<ExtendedVideoInformation> extendedVideoInformations = new ArrayList<>();

        for(Video video: videoList) {
            User user = userDAO.findUserById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation =
                    new ExtendedVideoInformation(video.getId(), video.getUrl(), video.getTitle(), video.getDescription(),
                            user.getFirstName(),user.getLastName(), user.getId(), video.getDisplay(),
                            video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes());

            extendedVideoInformations.add(extendedVideoInformation);
        }

        return extendedVideoInformations;
    }

}
