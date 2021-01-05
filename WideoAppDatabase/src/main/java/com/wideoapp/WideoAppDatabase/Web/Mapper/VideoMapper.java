package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;
import org.mapstruct.Mapper;

@Mapper
public interface VideoMapper {
    Video videoDtoToVideo(VideoDto videoDto);
    VideoDto videoToVideoDto(Video video);
}
