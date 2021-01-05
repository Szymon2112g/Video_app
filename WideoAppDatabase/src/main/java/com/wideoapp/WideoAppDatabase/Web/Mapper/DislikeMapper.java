package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Dislike;
import com.wideoapp.WideoAppDatabase.Web.Model.DislikeDto;
import org.mapstruct.Mapper;

@Mapper
public interface DislikeMapper {
    Dislike dislikeDtoToDislike(DislikeDto dislikeDto);
    DislikeDto dislikeToDislikeDto(Dislike dislike);
}
