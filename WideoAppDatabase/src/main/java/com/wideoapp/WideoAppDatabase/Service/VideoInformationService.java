package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;

import java.util.List;

public interface VideoInformationService {
    ExtendedVideoInformation findById(int id);
    List<ExtendedVideoInformation> findLatestVideo();
    List<ExtendedVideoInformation> findTheMostDisplayVideo();
    List<ExtendedVideoInformation> findTheMostLikesVideo();
    List<ExtendedVideoInformation> findTheMostDislikesVideo();
    List<ExtendedVideoInformation> getVideosOnTime();
    List<String> findTipsByKey(String key);
    List<ExtendedVideoInformation> findVideoByKey(String key);
    void increaseDisplay(ExtendedVideoInformation theVideo);
    List<ExtendedVideoInformation> findVideoByUserId(int userId);
}
