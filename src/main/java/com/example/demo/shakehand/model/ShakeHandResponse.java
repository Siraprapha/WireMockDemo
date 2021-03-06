package com.example.demo.shakehand.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class ShakeHandResponse implements Serializable {
    Boolean isShaked;
    String message;
}
