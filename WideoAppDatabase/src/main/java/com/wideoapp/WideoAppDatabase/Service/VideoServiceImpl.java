package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Web.Mapper.VideoMapper;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoDAO videoDAO;
    private VideoMapper videoMapper = Mappers.getMapper(VideoMapper.class);

    @Autowired
    public VideoServiceImpl(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    @Transactional
    public VideoDto findById(int id) {
        Video video = videoDAO.findById(id);
        VideoDto videoDto = videoMapper.videoToVideoDto(video);
        return videoDto;
    }

    @Override
    @Transactional
    public void increaseDisplay(VideoDto theVideo) {
        Video video = videoMapper.videoDtoToVideo(theVideo);
        videoDAO.increaseDisplay(video);
    }

    @Override
    @Transactional
    public List<VideoDto> findByTableColumn(String category) {
        List<Video> videoList = videoDAO.findByTableColumn(category);
        List<VideoDto> videoDtoList = new ArrayList<>(videoList.size());

        for (Video video: videoList) {
            VideoDto videoDto = videoMapper.videoToVideoDto(video);
            videoDtoList.add(videoDto);
        }

        return videoDtoList;
    }

    @Override
    @Transactional
    public List<VideoDto> findAllOrderByDateDesc() {
        List<Video> videoList =  videoDAO.findAllOrderByDateDesc();
        List<VideoDto> videoDtoList = new ArrayList<>(videoList.size());

        for (Video video: videoList) {
            VideoDto videoDto = videoMapper.videoToVideoDto(video);
            videoDtoList.add(videoDto);
        }

        return videoDtoList;
    }

    @Override
    @Transactional
    public List<String> findTipsByKey(String key) {
        return videoDAO.findTipsByKey(key);
    }

    @Override
    @Transactional
    public List<VideoDto> findVideoByKey(String key) {
        List<Video> videoList = videoDAO.findVideoByKey(key);
        List<VideoDto> videoDtoList = new ArrayList<>(videoList.size());

        for (Video video: videoList) {
            VideoDto videoDto = videoMapper.videoToVideoDto(video);
            videoDtoList.add(videoDto);
        }

        return videoDtoList;
    }

    @Override
    @Transactional
    public List<VideoDto> findVideoByUserId(int id) {
        List<Video> videoList = videoDAO.findVideoByUserId(id);
        List<VideoDto> videoDtoList = new ArrayList<>(videoList.size());

        for (Video video: videoList) {
            VideoDto videoDto = videoMapper.videoToVideoDto(video);
            videoDtoList.add(videoDto);
        }

        return videoDtoList;
    }
}
