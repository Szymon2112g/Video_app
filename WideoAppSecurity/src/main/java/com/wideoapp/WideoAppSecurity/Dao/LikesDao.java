package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikesDao extends JpaRepository<Likes, Long> {

    @Transactional
    public void removeByVideoIdAndUserId(int VideoId, int UserId);

    @Transactional
    public List<Likes> findAllByUserIdOrderByIdDesc(int userId);
}
