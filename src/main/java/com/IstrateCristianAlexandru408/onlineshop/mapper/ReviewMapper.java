package com.IstrateCristianAlexandru408.onlineshop.mapper;

import com.IstrateCristianAlexandru408.onlineshop.dto.Review;
import com.IstrateCristianAlexandru408.onlineshop.entity.ReviewEntity;

public class ReviewMapper {
    private static ReviewMapper instance;

    private ReviewMapper() {}

    public static ReviewMapper getInstance() {
        if (instance == null) {
            instance = new ReviewMapper();
        }
        return instance;
    }

    public Review toDto(ReviewEntity reviewEntity) {
        Review review = new Review();
        review.setId(reviewEntity.getId());
        review.setContent(reviewEntity.getContent());
        review.setRating(reviewEntity.getRating());
        review.setUserId(reviewEntity.getUser().getId());
        review.setProductId(reviewEntity.getProduct().getId());
        return review;
    }
}