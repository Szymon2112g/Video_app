package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoDao extends JpaRepository<Video, Integer> {

    Optional<Video> findById(int id);
    List<Video> findAllByUserIdOrderByDateDesc(int userId);
}
