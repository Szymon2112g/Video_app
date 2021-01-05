package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.Dislike;
import com.wideoapp.WideoAppSecurity.Web.Model.DislikeDto;
import org.mapstruct.Mapper;

@Mapper
public interface DislikeMapper {
    Dislike dislikeDtoToDislike(DislikeDto dislikeDto);
    DislikeDto dislikeToDislikeDto(Dislike dislike);
}
