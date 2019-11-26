package com.example.demo.shakehand.service;

import com.example.demo.shakehand.model.ShakeHandRequest;
import com.example.demo.shakehand.model.ShakeHandResponse;
import org.springframework.stereotype.Service;

@Service
public class ShakeHandService {

    public ShakeHandResponse getShakeHandResult(ShakeHandRequest shakeHandRequest) {
        ShakeHandResponse shakeHandResponse = new ShakeHandResponse();
        if (shakeHandRequest.getName().equalsIgnoreCase(shakeHandRequest.getShakeHandTo())) {
            shakeHandResponse.setShaked(false);
            shakeHandResponse.setMessage("You can't say hi to yourself.");
        } else {
            shakeHandResponse.setShaked(true);
            shakeHandResponse.setMessage("Hello " + shakeHandRequest.getName() + ", nice to meet you.");
        }
        return shakeHandResponse;
    }
}
