package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.Video;
import com.wideoapp.WideoAppSecurity.Web.Model.VideoDto;
import org.mapstruct.Mapper;

@Mapper
public interface VideoMapper {
    Video videoDtoToVideo(VideoDto videoDto);
    VideoDto videoToVideoDto(Video video);
}
