package com.example.demo.sayhi.controller;

import com.example.demo.sayhi.model.SayHiRequest;
import com.example.demo.sayhi.model.SayHiResponse;
import com.example.demo.sayhi.service.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SayHiController {
    @Autowired
    private SayHiService service;

    @PostMapping(value = "/sayHi", produces = MediaType.APPLICATION_JSON_VALUE)
    public SayHiResponse sayHi(@RequestBody SayHiRequest sayHiRequest) throws IOException {
        return service.shakeHand(sayHiRequest);
    }
}
