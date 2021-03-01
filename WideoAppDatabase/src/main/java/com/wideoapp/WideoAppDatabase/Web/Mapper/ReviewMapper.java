package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Review;
import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

    Review ReviewDtoToReview(ReviewDto reviewDto);
    ReviewDto ReviewToReviewDto(Review review);
}
