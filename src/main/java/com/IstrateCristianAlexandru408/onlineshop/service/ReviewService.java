package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<Review> getReviewsByProduct(Long productId);
    List<Review> getReviewsByUser(Long userId);
    Review createReview(Review review);
    Review updateReview(Review review);
    void deleteReview(Long id);
}
