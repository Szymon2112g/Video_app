package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.ReviewInformation;

import java.util.List;

public interface ReviewInformationService {
    public List<ReviewInformation> getAllReviewByVideoId(int id);
}
