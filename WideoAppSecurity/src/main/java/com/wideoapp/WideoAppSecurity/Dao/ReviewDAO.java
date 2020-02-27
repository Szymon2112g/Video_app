package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {

}
