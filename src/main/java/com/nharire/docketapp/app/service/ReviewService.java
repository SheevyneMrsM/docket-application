package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Review;
import com.nharire.docketapp.app.model.dto.ReviewDTO;
import com.nharire.docketapp.app.model.dto.response.ReviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {

    ReviewResponse saveReviews(ReviewDTO reviewDTO);

    ReviewDTO updateReviews(ReviewDTO reviewDTO);

    void deleteReviews(Long id);

    List<Review> getAllReviews();

    Optional<Review> getReviews(Long id);






}
