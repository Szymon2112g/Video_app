package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao extends JpaRepository<Video, Long> {
    public Video findById(long id);
}
