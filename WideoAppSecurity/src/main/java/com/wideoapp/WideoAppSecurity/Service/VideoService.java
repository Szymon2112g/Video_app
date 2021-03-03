package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppSecurity.Web.Model.SmallVideoInformation;

import java.util.List;

public interface VideoService {
    void addSpecificVideoToDB(SmallVideoInformation smallVideoInformation);
    List<ExtendedVideoInformation> getVideoFeedHistory(int id, String email);
    List<ExtendedVideoInformation> getVideoFeedLiked(int id, String email);
    List<ExtendedVideoInformation> getVideoFeedSubscription(int id, String email);
}
