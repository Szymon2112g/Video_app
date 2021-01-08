package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Web.Model.SubscribedUser;
import java.util.List;

public interface SubscribeService {
    public List<SubscribedUser> getUserSubscriptions(String email);
    @Deprecated
    public void addSubscription(String email, int subscribedUser);
    @Deprecated
    public void subtractSubscription(String email, int subscribedUser);
}
