package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.*;
import com.wideoapp.WideoAppSecurity.Entity.*;
import com.wideoapp.WideoAppSecurity.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppSecurity.Web.Model.SmallVideoInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional<User> userOptional = userDao.findByEmail(smallVideoInformation.getEmail());

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        User user = userOptional.get();

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

        User user = findUserByEmail(email);

        List<History> historyList = historyDao.findAllByUserIdOrderByIdDesc(user.getId());

        List<ExtendedVideoInformation> videoInformation = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= historyList.size()) {
                continue;
            }

            Video video = findVideoById(historyList.get(i).getVideoId());

            User userVideo = findUserById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformation.add(extendedVideoInformation);
        }

        return videoInformation;
    }

    @Override
    public List<ExtendedVideoInformation> getVideoFeedLiked(int id, String email) {

        User user = findUserByEmail(email);

        List<Likes> likes = likesDao.findAllByUserIdOrderByIdDesc(user.getId());

        List<ExtendedVideoInformation> videoInformation = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= likes.size()) {
                continue;
            }

            Video video = findVideoById(likes.get(i).getVideoId());

            User userVideo =findUserById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformation.add(extendedVideoInformation);
        }

        return videoInformation;
    }

    @Override
    public List<ExtendedVideoInformation> getVideoFeedSubscription(int id, String email) {

        User user = findUserByEmail(email);

        List<Subscribe> subscribes = subscribeDao.findAllByUserId(user.getId());

        List<ExtendedVideoInformation> videoInformation = new ArrayList<>();

        List<Video> videoList = new ArrayList<>();

        for(Subscribe tmpSubscribe: subscribes) {
            User tmpUser = findUserById(tmpSubscribe.getUserSubscriptionId());
            videoList.addAll(videoDao.findAllByUserIdOrderByDateDesc(tmpUser.getId()));
        }

        videoList.sort(((o1, o2) -> Integer.compare(o2.getId(), o1.getId())));

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= videoList.size()) {
                continue;
            }

            Video video = videoList.get(i);
            User userVideo = findUserById(video.getUserId());

            ExtendedVideoInformation extendedVideoInformation = createExtendedVideoInformation(userVideo, video);

            videoInformation.add(extendedVideoInformation);
        }

        return videoInformation;
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

    private Video findVideoById(int id) {

        Optional<Video> videoToSaveOptional = videoDao.findById(id);

        if (!videoToSaveOptional.isPresent()) {
            throw new IllegalStateException("No found Video");
        }

        return videoToSaveOptional.get();
    }

    private User findUserByEmail(String email) {

        Optional<User> userOptional = userDao.findByEmail(email);

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        return userOptional.get();
    }

    private User findUserById(int id) {

        Optional<User> userOptional = userDao.findById(id);

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        return userOptional.get();
    }
}
