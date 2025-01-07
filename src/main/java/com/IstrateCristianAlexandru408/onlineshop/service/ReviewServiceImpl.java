package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Review;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.ReviewEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.ProductNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.exception.ReviewNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.ReviewMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.ProductRepository;
import com.IstrateCristianAlexandru408.onlineshop.repository.ReviewRepository;
import com.IstrateCristianAlexandru408.onlineshop.repository.UserRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(ReviewMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(ReviewMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Review createReview(Review review) {
        UserEntity user = userRepository.findById(review.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format(ErrorMessageUtils.USER_NOT_FOUND, review.getUserId())));
        ProductEntity product = productRepository.findById(review.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(String.format(ErrorMessageUtils.PRODUCT_NOT_FOUND, review.getProductId())));

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent(review.getContent());
        reviewEntity.setRating(review.getRating());
        reviewEntity.setUser(user);
        reviewEntity.setProduct(product);

        return ReviewMapper.getInstance().toDto(reviewRepository.save(reviewEntity));
    }

    @Override
    public Review updateReview(Review review) {
        ReviewEntity reviewEntity = reviewRepository.findById(review.getId())
                .orElseThrow(() -> new ReviewNotFoundException(String.format(ErrorMessageUtils.REVIEW_NOT_FOUND, review.getId())));

        reviewEntity.setContent(review.getContent());
        reviewEntity.setRating(review.getRating());
        return ReviewMapper.getInstance().toDto(reviewRepository.save(reviewEntity));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(String.format(ErrorMessageUtils.REVIEW_NOT_FOUND, id)));
        reviewRepository.deleteById(id);
    }



}