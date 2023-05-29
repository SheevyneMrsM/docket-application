package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Review;
import com.nharire.docketapp.app.model.dto.ReviewDTO;
import com.nharire.docketapp.app.repository.ReviewRepo;
import com.nharire.docketapp.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    
    @Override
    public Review saveReviews(ReviewDTO reviewDTO) {
        log.info("SAVE REVIEWS: {}", reviewDTO.toString());
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO,review);
        log.info("Saving reviews: {}", review);
        return reviewRepo.save(review);
    }

    @Override
    public ReviewDTO updateReviews(ReviewDTO reviewDTO) {
        return null;
    }

    @Override
    public void deleteReviews(Long id) {

    }

    @Override
    public List<ReviewDTO> addReviews(Review review) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return null;
    }

    @Override
    public Optional<Review> getReviews(String body) {
        return Optional.empty();
    }
}
