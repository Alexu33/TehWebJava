package com.IstrateCristianAlexandru408.onlineshop.controller;

import com.IstrateCristianAlexandru408.onlineshop.dto.Review;
import com.IstrateCristianAlexandru408.onlineshop.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private Review mockReview;

    @BeforeEach
    void setUp() {
        mockReview = new Review();
        mockReview.setId(1L);
        mockReview.setContent("Great product!");
    }

    @Test
    void testGetAllReviews() throws Exception {
        when(reviewService.getAllReviews()).thenReturn(List.of(mockReview));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Great product!"));
    }

    @Test
    void testGetReviewsByProduct() throws Exception {
        when(reviewService.getReviewsByProduct(1L)).thenReturn(List.of(mockReview));

        mockMvc.perform(get("/reviews/product/{productId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Great product!"));
    }

    @Test
    void testCreateReview() throws Exception {
        Review mockReview = new Review();
        mockReview.setId(1L);
        mockReview.setContent("Great product!");
        mockReview.setRating(5);
        mockReview.setUserId(1L);
        mockReview.setProductId(101L);

        when(reviewService.createReview(any(Review.class))).thenReturn(mockReview);

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"Great product!\",\"rating\":5,\"userId\":1,\"productId\":101}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Great product!"))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.productId").value(101));
    }

    @Test
    void testDeleteReview() throws Exception {
        doNothing().when(reviewService).deleteReview(1L);

        mockMvc.perform(delete("/reviews/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
