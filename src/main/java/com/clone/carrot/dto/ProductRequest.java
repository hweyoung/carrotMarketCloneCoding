package com.clone.carrot.dto;

import com.clone.carrot.domain.Product;
import com.clone.carrot.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class create {
        private String region;
        private String title;
        private String contents;
        private String category;
        private int price;
        private String status;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class update{
        private Long productIdx;
        private String region;
        private String title;
        private String contents;
        private String category;
        private int price;
        private String status;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class idx {
        private Long productIdx;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class delete {
        private Long productId;
    }

}
