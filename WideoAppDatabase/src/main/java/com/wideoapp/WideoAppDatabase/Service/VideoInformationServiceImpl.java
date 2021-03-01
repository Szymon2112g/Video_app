package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.UserDAO;
import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VideoInformationServiceImpl implements VideoInformationService {

    private VideoDAO videoDAO;
    private UserDAO userDAO;

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

        ExtendedVideoInformation extendedVideoInformation = ExtendedVideoInformation.getBuilder()
                .setId(video.getId())
                .setUrl(video.getUrl())
                .setTitle(video.getTitle())
                .setDescription(video.getDescription())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUserId(user.getId())
                .setDisplay(video.getDisplay())
                .setPhotoUrl(video.getPhotoUrl())
                .setDate(video.getDate())
                .setLikes(video.getLikes())
                .setDislikes(video.getDislikes())
                .build();

        return extendedVideoInformation;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findLatestVideo() {
        List<Video> videoList = videoDAO.findLatestVideo();

        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findTheMostDisplayVideo() {
        List<Video> videoList = videoDAO.findTheMostDisplayVideo();

        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findTheMostLikesVideo() {
        List<Video> videoList = videoDAO.findTheMostLikesVideo();

        List<ExtendedVideoInformation> extendedVideoInformations =
                swapVideoListToExtendedVideoInformationList(videoList);

        return extendedVideoInformations;
    }

    @Override
    @Transactional
    public List<ExtendedVideoInformation> findTheMostDislikesVideo() {
        List<Video> videoList = videoDAO.findTheMostDislikesVideo();

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
    public void increaseDisplay(ExtendedVideoInformation theVideo) {
        Video video = new Video();
        video.setId(theVideo.getId());
        video.setDisplay(theVideo.getDisplay());
        videoDAO.increaseDisplay(video);
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

            ExtendedVideoInformation extendedVideoInformation = ExtendedVideoInformation.getBuilder()
                    .setId(video.getId())
                    .setUrl(video.getUrl())
                    .setTitle(video.getTitle())
                    .setDescription(video.getDescription())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setUserId(user.getId())
                    .setDisplay(video.getDisplay())
                    .setPhotoUrl(video.getPhotoUrl())
                    .setDate(video.getDate())
                    .setLikes(video.getLikes())
                    .setDislikes(video.getDislikes())
                    .build();

            extendedVideoInformations.add(extendedVideoInformation);
        }

        return extendedVideoInformations;
    }

}
