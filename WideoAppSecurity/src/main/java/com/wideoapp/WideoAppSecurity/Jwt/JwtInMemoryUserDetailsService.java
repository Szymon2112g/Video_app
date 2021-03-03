package com.wideoapp.WideoAppSecurity.Jwt;

import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public JwtInMemoryUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> userOptional = userDao.findByEmail(s);

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        User user = userOptional.get();

        JwtUserDetails jwtUserDetails = new JwtUserDetails(user);
        return jwtUserDetails;
    }
}
