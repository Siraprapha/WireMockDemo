package com.example.demo.shakehand;

import com.example.demo.shakehand.model.ShakeHandRequest;
import com.example.demo.shakehand.model.ShakeHandResponse;
import com.example.demo.shakehand.service.ShakeHandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShakeHandController {
    @Autowired
    private ShakeHandService service;

    @PostMapping(value = "/shakeHand", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShakeHandResponse shakeHand(@RequestBody ShakeHandRequest shakeHandRequest) {
        return service.getShakeHandResult(shakeHandRequest);
    }
}
