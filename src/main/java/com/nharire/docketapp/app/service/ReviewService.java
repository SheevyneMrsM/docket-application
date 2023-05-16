package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Review;
import com.nharire.docketapp.app.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {

    Review saveReviews(ReviewDTO reviewDTO);

    ReviewDTO updateReviews(ReviewDTO reviewDTO);

    void deleteReviews(Long id);

    List<ReviewDTO> addReviews(Review review);

    List<ReviewDTO> getAllReviews();

    Optional<ReviewDTO> getReviews(String body);






}
