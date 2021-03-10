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

        List<Subscribe> foundSubscribe = subscribeDao.findAllByUserId(user.getId());

        for(Subscribe subscribe : foundSubscribe) {
            User subUser = findUserById(subscribe.getUserSubscriptionId());
            String name = subUser.getFirstName() + " " + subUser.getLastName();
            SubscribedUser subscribedUser = new SubscribedUser(subUser.getId(), subUser.getEmail(), name);
            subscribes.add(subscribedUser);
        }

        return subscribes;
    }

    @Override
    public boolean addSubscription(String email, int subscribedUserId) {

        User user = findUserByEmail(email);

        if (user == null) {
            return false;
        }

        if (subscribeDao.existsByUserIdAndUserSubscriptionId(user.getId(), subscribedUserId)) {
            return false;
        }

        user.getSubscribeList().add(new Subscribe(0, subscribedUserId, user.getId()));

        userDao.save(user);

        return true;
    }

    @Override
    public boolean subtractSubscription(String email, int subscribedUserId) {

        User user = findUserByEmail(email);

        if (user == null) {
            return false;
        }

        if (!subscribeDao.existsByUserIdAndUserSubscriptionId(user.getId(), subscribedUserId)) {
            return false;
        }

        subscribeDao.removeByUserSubscriptionIdAndUserId(subscribedUserId, user.getId());

        return true;
    }

    private User findUserByEmail(String email) {

        Optional<User> userOptional = userDao.findByEmail(email);

        if (!userOptional.isPresent()) {
            return null;
        }

        return userOptional.get();
    }

    private User findUserById(int id) {

        Optional<User> userOptional = userDao.findById(id);

        if (!userOptional.isPresent()) {
            return null;
        }

        return userOptional.get();
    }

}
