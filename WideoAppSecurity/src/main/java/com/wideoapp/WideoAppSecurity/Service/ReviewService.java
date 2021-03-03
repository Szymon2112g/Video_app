package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;

public interface ReviewService {
    public void addReview(String email, ReviewToAdd reviewToAdd);
}
