package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DislikesDao extends JpaRepository<Dislike, Long> {

    @Transactional
    void removeByVideoIdAndUserId(int VideoId, int UserId);

    boolean existsByVideoIdAndUserId(int videoId, int userId);
}
