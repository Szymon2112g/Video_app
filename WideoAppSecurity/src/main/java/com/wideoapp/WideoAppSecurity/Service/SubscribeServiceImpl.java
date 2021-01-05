package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.SubscribeDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Web.Model.GetSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService{

    private UserDao userDao;
    private SubscribeDao subscribeDao;

    @Autowired
    public SubscribeServiceImpl(UserDao userDao, SubscribeDao subscribeDao) {
        this.userDao = userDao;
        this.subscribeDao = subscribeDao;
    }

    @Override
    public List<GetSubscriptions> getUserSubscriptions(String email) {

        User user = userDao.findByEmail(email);
        List<GetSubscriptions> subscribes = new ArrayList<>();

        for(Subscribe tmpSub : user.getSubscribeList()) {
            User userTmp = userDao.findById(tmpSub.getUserSubscriptionId());
            String name = userTmp.getFirstName() + " " + userTmp.getLastName();
            GetSubscriptions  getSubscriptions = new GetSubscriptions(userTmp.getId(), userTmp.getEmail(), name);
            subscribes.add(getSubscriptions);
        }

        return subscribes;
    }

    @Override
    public void addSubscription(String email, int subscribedUser) {
        User user = userDao.findByEmail(email);

        user.getSubscribeList().add(new Subscribe(0, subscribedUser, user.getId()));

        userDao.save(user);
    }

    @Override
    public void subtractSubscription(String email, int subscribedUser) {
        User user = userDao.findByEmail(email);
        subscribeDao.removeByUserSubscriptionIdAndUserId(subscribedUser, user.getId());
    }
}
