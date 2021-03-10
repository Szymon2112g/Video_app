package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;

public interface ReviewService {
    public boolean addReview(String email, ReviewToAdd reviewToAdd);
}
