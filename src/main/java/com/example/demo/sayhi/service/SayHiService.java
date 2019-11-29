package com.example.demo.sayhi.service;

import com.example.demo.sayhi.model.SayHiRequest;
import com.example.demo.sayhi.model.SayHiResponse;
import com.example.demo.shakehand.model.ShakeHandRequest;
import com.example.demo.shakehand.model.ShakeHandResponse;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SayHiService {

    public SayHiResponse shakeHand(SayHiRequest sayHiRequest) {
        ShakeHandRequest shakeHandRequest = ShakeHandRequest.builder()
                .name(sayHiRequest.getName())
                .shakeHandTo(sayHiRequest.getSayHiTo())
                .message(sayHiRequest.getMessage())
                .build();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });
        ShakeHandResponse shakeHandResponse = restTemplate.postForObject("http://localhost:8089/shakeHand", shakeHandRequest, ShakeHandResponse.class);

        assert shakeHandResponse != null;
        return SayHiResponse.builder()
                .isShaked(shakeHandResponse.getIsShaked())
                .message(shakeHandResponse.getMessage())
                .build();
    }
}
