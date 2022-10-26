package com.clone.carrot.dto;

import com.clone.carrot.domain.Product;
import com.clone.carrot.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Locale;

public class ProductResponse {
    @NoArgsConstructor
    @Builder
    @Data
    public static class create{
        private Long productIdx;
        public create(Long idx){
            this.productIdx = idx;
        }
    }

    @NoArgsConstructor
    @Data
    public static class getProduct {
        setProduct product;
        setUser user;

        public getProduct(Product product){
            this.product = new setProduct(product);
            this.user = new setUser(product);
        }
    }

    @NoArgsConstructor
    @Data
    public static class setProduct {
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Long productIDx;
        private String region;
        private String title;
        private String contents;
        private int price;
        private String category;
        private String status;
        public setProduct(Product product){
            this.createdAt = product.getCreatedAt();
            this.updatedAt = product.getModifiedAt();
            this.productIDx = product.getProductIdx();
            this.region = product.getRegion();
            this.title = product.getTitle();
            this.contents = product.getContents();
            this.price = product.getPrice();
            this.category = product.getCategory();
            this.status = product.getStatus();
        }
    }

    @NoArgsConstructor
    @Data
    public static class setUser {
        private String nickname;
        private String code;
        private double mannerTemp;
        private String img;
        public setUser(Product product){
            this.nickname = product.getUser().getNickname();
            this.code = product.getUser().getCode();
            this.mannerTemp = product.getUser().getMannerTemp();
            this.img = product.getUser().getImg();
        }
    }

    @NoArgsConstructor
    @Data
    public static class update{
        private Long productIdx;
        public update(Long idx){
            this.productIdx = idx;
        }
    }


}
