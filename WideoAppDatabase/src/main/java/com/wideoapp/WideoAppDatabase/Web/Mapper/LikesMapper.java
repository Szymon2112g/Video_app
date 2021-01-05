package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Likes;
import com.wideoapp.WideoAppDatabase.Web.Model.LikesDto;
import org.mapstruct.Mapper;

@Mapper
public interface LikesMapper {
    Likes likesDtoToLikes(LikesDto likesDto);
    LikesDto likesToLikesDto(Likes likes);
}
