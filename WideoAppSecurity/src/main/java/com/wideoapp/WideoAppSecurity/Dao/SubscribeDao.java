package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SubscribeDao extends JpaRepository<Subscribe, Long> {

    @Transactional
    public void removeByUserSubscriptionIdAndUserId(int VideoId, int UserId);
}
