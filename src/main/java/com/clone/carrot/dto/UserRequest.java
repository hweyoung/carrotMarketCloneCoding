package com.clone.carrot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class join{
        private String email;
        private String name;
        private String loginId;
        private String image;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class login {
        private String phone;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class checknum {
        private String sendMessage;
        private String userMessage;
        private String phone;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class joinUser {
        private String phone;
        private String nickname;
        private String email;
        private String region;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class loginUser{
        private String phone;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class code {
        private String userCode;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class update {
        private String userCode;
        private String nickname;
        private String email;
        private double mannerTemp;
        private String region;
    }



}
