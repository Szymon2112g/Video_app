package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubscribeDao extends JpaRepository<Subscribe, Long> {

    @Transactional
    void removeByUserSubscriptionIdAndUserId(int VideoId, int UserId);

    @Transactional
    List<Subscribe> findAllByUserId(int userId);

    boolean existsByUserIdAndUserSubscriptionId(int userId, int userSubscriptionId);

}
