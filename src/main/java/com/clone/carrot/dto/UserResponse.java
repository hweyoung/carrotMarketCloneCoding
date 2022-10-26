package com.clone.carrot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class randomNum {
        private String randomNum;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class get {
        private String nickname;
        private String phone;
        private String email;
        private double mannerTemp;
        private String code;
        private String status;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class response{
        private String token;
        private String userCode;
    }
}
