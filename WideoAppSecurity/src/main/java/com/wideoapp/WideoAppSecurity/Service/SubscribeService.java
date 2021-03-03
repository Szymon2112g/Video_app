package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Web.Model.SubscribedUser;
import java.util.List;

public interface SubscribeService {
    public List<SubscribedUser> getUserSubscriptions(String email);
    public void addSubscription(String email, int subscribedUser);
    public void subtractSubscription(String email, int subscribedUser);
}
