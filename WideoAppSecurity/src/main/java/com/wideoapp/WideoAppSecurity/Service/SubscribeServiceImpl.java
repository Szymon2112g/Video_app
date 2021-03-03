package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.SubscribeDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import com.wideoapp.WideoAppSecurity.Web.Model.SubscribedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<SubscribedUser> getUserSubscriptions(String email) {

        User user = findUserByEmail(email);

        List<SubscribedUser> subscribes = new ArrayList<>();

        for(Subscribe subscribe : user.getSubscribeList()) {
            User subUser = userDao.findById(subscribe.getUserSubscriptionId());
            String name = subUser.getFirstName() + " " + subUser.getLastName();
            SubscribedUser subscribedUser = new SubscribedUser(subUser.getId(), subUser.getEmail(), name);
            subscribes.add(subscribedUser);
        }

        return subscribes;
    }

    @Override
    public void addSubscription(String email, int subscribedUserId) {

        User user = findUserByEmail(email);

        user.getSubscribeList().add(new Subscribe(0, subscribedUserId, user.getId()));

        userDao.save(user);
    }

    @Override
    public void subtractSubscription(String email, int subscribedUserId) {

        User user = findUserByEmail(email);

        subscribeDao.removeByUserSubscriptionIdAndUserId(subscribedUserId, user.getId());
    }

    private User findUserByEmail(String email) {

        Optional<User> userOptional = userDao.findByEmail(email);

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        return userOptional.get();
    }

}
