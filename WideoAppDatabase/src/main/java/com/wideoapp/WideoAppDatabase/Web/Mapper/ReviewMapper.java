package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Review;
import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
@Mapping(source = "videoDto", target = "video")
    @Mapping(source = "userDto", target = "user")

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "video", target = "videoDto")
 */

@Mapper
public interface ReviewMapper {

    Review ReviewDtoToReview(ReviewDto reviewDto);
    ReviewDto ReviewToReviewDto(Review review);
}
