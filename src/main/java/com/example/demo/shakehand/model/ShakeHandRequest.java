package com.example.demo.shakehand.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ShakeHandRequest implements Serializable {
    String name;
    String shakeHandTo;
    String message;
}
