package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;

import java.util.List;

public interface ReviewService {
    public List<ReviewDto> getAllReviewByVideoId(int id);
}
