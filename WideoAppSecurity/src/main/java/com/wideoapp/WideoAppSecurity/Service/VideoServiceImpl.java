package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.*;
import com.wideoapp.WideoAppSecurity.Entity.*;
import com.wideoapp.WideoAppSecurity.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppSecurity.Web.Model.SmallVideoInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoDao videoDao;
    private UserDao userDao;
    private HistoryDao historyDao;
    private LikesDao likesDao;
    private SubscribeDao subscribeDao;

    @Autowired
    public VideoServiceImpl(VideoDao videoDao, UserDao userDao, HistoryDao historyDao, LikesDao likesDao, SubscribeDao subscribeDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
        this.historyDao = historyDao;
        this.likesDao = likesDao;
        this.subscribeDao = subscribeDao;
    }

    @Override
    public void addSpecificVideoToDB(SmallVideoInformation smallVideoInformation) {
        User user = userDao.findByEmail(smallVideoInformation.getEmail());

        String currentDate = (java.time.LocalDate.now()).toString();

        Video video = new Video(
                smallVideoInformation.getUrl(),
                smallVideoInformation.getTitle(),
                smallVideoInformation.getDescription(),
                0,
                0,
                0,
                currentDate,
                user.getId(),
                null,
                smallVideoInformation.getPhotoUrl()
        );

        user.getVideoList().add(video);
        userDao.save(user);
    }

    @Override
    public List<ExtendedVideoInformation> getVideoFeedHistory(int id, String email) {

        User user = userDao.findByEmail(email);
        List<History> historyList = historyDao.findAllByUserIdOrderByIdDesc(user.getId());
        List<ExtendedVideoInformation> videoInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= historyList.size()) {
                continue;
            }
            Video video = videoDao.findById(historyList.get(i).getVideoId());
            User userVideo = userDao.findById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformations.add(extendedVideoInformation);
        }

        return videoInformations;
    }

    @Override
    public List<ExtendedVideoInformation> getVideoFeedLiked(int id, String email) {

        User user = userDao.findByEmail(email);
        List<Likes> likes = likesDao.findAllByUserIdOrderByIdDesc(user.getId());
        List<ExtendedVideoInformation> videoInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= likes.size()) {
                continue;
            }

            Video video = videoDao.findById(likes.get(i).getVideoId());
            User userVideo = userDao.findById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformations.add(extendedVideoInformation);
        }

        return videoInformations;
    }

    @Override
    public List<ExtendedVideoInformation> getVideoFeedSubscription(int id, String email) {

        User user = userDao.findByEmail(email);
        List<Subscribe> subscribes = subscribeDao.findAllByUserId(user.getId());

        List<ExtendedVideoInformation> videoInformations = new ArrayList<>();
        List<Video> videoList = new ArrayList<>();

        for(Subscribe tmpSubscribe: subscribes) {
            User tmpUser = userDao.findById(tmpSubscribe.getUserSubscriptionId());
            videoList.addAll(videoDao.findAllByUserIdOrderByDateDesc(tmpUser.getId()));
        }

        videoList.sort(((o1, o2) -> Integer.compare(o2.getId(), o1.getId())));

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= videoList.size()) {
                continue;
            }

            Video video = videoList.get(i);
            User userVideo = userDao.findById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformations.add(extendedVideoInformation);
        }

        return videoInformations;
    }

    private ExtendedVideoInformation createExtendedVideoInformation(User user, Video video) {
        ExtendedVideoInformation extendedVideoInformation = ExtendedVideoInformation.builder()
                .date(video.getDate())
                .description(video.getDescription())
                .dislikes(video.getDislikes())
                .display(video.getDisplay())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userId(video.getUserId())
                .url(video.getUrl())
                .id(video.getId())
                .likes(video.getLikes())
                .photoUrl(video.getPhotoUrl())
                .title(video.getTitle())
                .build();

        return extendedVideoInformation;
    }

}
