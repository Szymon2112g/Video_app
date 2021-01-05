package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.Likes;
import com.wideoapp.WideoAppSecurity.Web.Model.LikesDto;
import org.mapstruct.Mapper;

@Mapper
public interface LikesMapper {
    Likes likesDtoToLikes(LikesDto likesDto);
    LikesDto likesToLikesDto(Likes likes);
}
