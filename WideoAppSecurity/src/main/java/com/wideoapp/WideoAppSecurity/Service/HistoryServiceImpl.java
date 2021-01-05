package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.History;
import com.wideoapp.WideoAppSecurity.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    private UserDao userDao;

    @Autowired
    public HistoryServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addHistory(String email, int videoId) {
        User user = userDao.findByEmail(email);
        user.getHistoryList().add(new History(0, videoId, user.getId()));
        userDao.save(user);
    }
}
