package com.example.demo.sayhi.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SayHiResponse implements Serializable {
    Boolean isShaked;
    String message;
}
