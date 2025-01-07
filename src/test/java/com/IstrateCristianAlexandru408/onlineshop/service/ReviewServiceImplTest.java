package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Review;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.ReviewEntity;
import com.IstrateCristianAlexandru408.onlineshop.entity.UserEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.ProductNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.exception.ReviewNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.exception.UserNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.repository.ProductRepository;
import com.IstrateCristianAlexandru408.onlineshop.repository.ReviewRepository;
import com.IstrateCristianAlexandru408.onlineshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private ReviewEntity mockReview;
    private UserEntity mockUser;
    private ProductEntity mockProduct;

    @BeforeEach
    void setUp() {
        mockUser = new UserEntity();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");

        mockProduct = new ProductEntity();
        mockProduct.setId(1L);
        mockProduct.setName("testproduct");

        mockReview = new ReviewEntity();
        mockReview.setId(1L);
        mockReview.setContent("Great product!");
        mockReview.setRating(5);
        mockReview.setUser(mockUser);
        mockReview.setProduct(mockProduct);
    }


    @Test
    void testGetAllReviews_Success() {
        when(reviewRepository.findAll()).thenReturn(List.of(mockReview));

        List<Review> reviews = reviewService.getAllReviews();

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(mockReview.getContent(), reviews.get(0).getContent());
    }

    @Test
    void testGetReviewsByProduct_Success() {
        when(reviewRepository.findByProductId(1L)).thenReturn(List.of(mockReview));

        List<Review> reviews = reviewService.getReviewsByProduct(1L);

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(mockReview.getContent(), reviews.get(0).getContent());
    }

    @Test
    void testGetReviewsByUser_Success() {
        when(reviewRepository.findByUserId(1L)).thenReturn(List.of(mockReview));

        List<Review> reviews = reviewService.getReviewsByUser(1L);

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals(mockReview.getContent(), reviews.get(0).getContent());
    }

    @Test
    void testCreateReview_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(mockReview);

        Review createdReview = reviewService.createReview(new Review(1L, "Great product!", 5, 1L, 1L));
        new Review(1L, "Great product!", 5, 1L, 1L);
        assertNotNull(createdReview);
        assertEquals(mockReview.getContent(), createdReview.getContent());
        assertEquals(mockReview.getRating(), createdReview.getRating());
    }

    @Test
    void testCreateReview_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            reviewService.createReview(new Review(1L, "Great product!", 5, 1L, 1L));
        });

        assertEquals("User with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testCreateReview_ProductNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            reviewService.createReview(new Review(1L, "Great product!", 5, 1L, 1L));
        });

        assertEquals("Product with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testUpdateReview_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(mockReview));
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(mockReview);

        Review updatedReview = reviewService.updateReview(new Review(1L, "Updated content", 4, 1L, 1L));

        assertNotNull(updatedReview);
        assertEquals("Updated content", updatedReview.getContent());
        assertEquals(4, updatedReview.getRating());
    }

    @Test
    void testUpdateReview_ReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        ReviewNotFoundException exception = assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.updateReview(new Review(1L, "Updated content", 4, 1L, 1L));
        });

        assertEquals("Review with id 1 Not Found", exception.getMessage());
    }

    @Test
    void testDeleteReview_Success() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(mockReview));

        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReview_ReviewNotFound() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        ReviewNotFoundException exception = assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.deleteReview(1L);
        });

        assertEquals("Review with id 1 Not Found", exception.getMessage());
    }

}