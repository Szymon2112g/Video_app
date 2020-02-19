package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;

import java.util.List;

public interface VideoDAO {
    public List<Video> findAll();
    public Video findById(int id);
}
