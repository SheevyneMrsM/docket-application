package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.Review;
import com.nharire.docketapp.app.model.dto.ReviewDTO;
import com.nharire.docketapp.app.model.dto.response.ReviewResponse;
import com.nharire.docketapp.app.repository.PoliceStationRepo;
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
    private final PoliceStationRepo policeStationRepo;
    
    @Override
    public ReviewResponse saveReviews(ReviewDTO reviewDTO) {
        ReviewResponse reviewResponse = new ReviewResponse();
        try {
            log.info("SAVE REVIEWS: {}", reviewDTO.toString());
        PoliceStation policeStation = new PoliceStation();
            Review review = new Review();

            if (reviewDTO != null){
            if (reviewDTO.getPoliceStationId()!= null) {
                Optional<PoliceStation> policeStation1 = policeStationRepo.findById(reviewDTO.getPoliceStationId());
                if (policeStation1.isPresent()) {
                    review.setPoliceStationId(policeStation.getId());
                    BeanUtils.copyProperties(reviewDTO, review);
                    log.info("Saving reviews: {}", review);
                    try {
                        review = reviewRepo.saveAndFlush(review);

                    } catch (Exception exception) {
                        reviewResponse.setResponseCode(500);
                        reviewResponse.setDescription("FAILED TO SAVE REVIEW");
                        reviewResponse.setMessage("failed to save review");
                        reviewResponse.setCode("DM-COMP-001");
                    }
                    policeStation = policeStation1.get();
                    BeanUtils.copyProperties(reviewDTO.getPoliceStationId(), policeStation);
                    policeStation = policeStationRepo.saveAndFlush(policeStation);
                } else {
                    reviewResponse.setResponseCode(404);
                    reviewResponse.setMessage("Police Station is not present! please get details!!!");
                }
            }
            BeanUtils.copyProperties(review,reviewResponse);
                reviewResponse.setPoliceStation(policeStation);
                reviewResponse.setResponseCode(200);
                reviewResponse.setMessage("SUCCESS");
                return reviewResponse;
            }}catch (Exception e){
            log.info("FAILED TO SAVE REVIEWS, DATABASE ERROR " + e);
            reviewResponse.setResponseCode(400);
            reviewResponse.setMessage("Failed to Save Information to Database");
            reviewResponse.setCode("DM-DB-001");
            reviewResponse.setDescription(e.getMessage());
        }
        return reviewResponse;
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
