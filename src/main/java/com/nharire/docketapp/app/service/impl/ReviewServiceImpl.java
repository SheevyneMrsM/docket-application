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
        Optional<Review> review = reviewRepo.findById(reviewDTO.getId());
        Review  review1;
        if (review.isPresent()){
            review1 = review.get();
            BeanUtils.copyProperties(reviewDTO,review1);
        }else {
            throw new RuntimeException("No details found, cant update!!!");
        }
        BeanUtils.copyProperties(review1,reviewDTO);
        return reviewDTO;
    }

    @Override
    public void deleteReviews(Long id) {
        reviewRepo.deleteById(id);

    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    @Override
    public Optional<Review> getReviews(Long id) {
        return reviewRepo.findById(id);
    }
}
