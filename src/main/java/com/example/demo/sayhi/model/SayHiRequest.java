package com.example.demo.sayhi.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SayHiRequest implements Serializable {
    String name;
    String sayHiTo;
    String message;
}
