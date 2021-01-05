package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.Review;
import com.wideoapp.WideoAppSecurity.Web.Model.ReviewDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewMapper {

    Review ReviewDtoToReview(ReviewDto reviewDto);
    ReviewDto ReviewToReviewDto(Review review);
}
