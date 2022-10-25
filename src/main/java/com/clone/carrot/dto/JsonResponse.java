package com.clone.carrot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    //    private final boolean success;
    private int status;
    private boolean isSuccess;
    private String message;
    private Object result;
}
