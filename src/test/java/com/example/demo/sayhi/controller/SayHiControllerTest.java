package com.example.demo.sayhi.controller;

import com.example.demo.sayhi.model.SayHiRequest;
import com.example.demo.sayhi.model.SayHiResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.listAllStubMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class SayHiControllerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success() {
        stubFor(post("/shakeHand")
                .willReturn(aResponse()
                        .withStatus(200)
//                        .withBody("{\"isShaked\": true,\r\n" + "\"message\": \"Yeahhhhh\"\r\n" + "}")));
                        .withBodyFile("SayHiToMeResponse.json")));

        System.out.println("stub: " + listAllStubMappings().getMappings().size());

        SayHiRequest sayHiRequest = SayHiRequest.builder()
                .name("ink")
                .sayHiTo("ong")
                .message("Hello Ink")
                .build();
        String url = String.format("http://localhost:%s/sayHi", port);
        SayHiResponse response = restTemplate.postForObject(url, sayHiRequest, SayHiResponse.class);

        verify(postRequestedFor(urlEqualTo("/shakeHand"))
                .withHeader("Content-Type", equalTo("application/json")));
        assertTrue(response.getIsShaked());
    }

    @Test
    public void failed() {
        stubFor(post("/shakeHand")
                .willReturn(aResponse()
                        .withStatus(200)
//                        .withBody("{\"isShaked\": false,\r\n" + "\"message\": \"Noooo\"\r\n" + "}")));
                        .withBodyFile("shakeHandToMeResponse.json")));

        System.out.println("stub: " + listAllStubMappings().getMappings().size());

        SayHiRequest sayHiRequest = SayHiRequest.builder()
                .name("ink")
                .sayHiTo("Ink")
                .message("Hello Ink")
                .build();
        String url = String.format("http://localhost:%s/sayHi", port);
        SayHiResponse response = restTemplate.postForObject(url, sayHiRequest, SayHiResponse.class);

        verify(postRequestedFor(urlEqualTo("/shakeHand"))
                .withHeader("Content-Type", equalTo("application/json")));
        assertFalse(response.getIsShaked());
    }
}