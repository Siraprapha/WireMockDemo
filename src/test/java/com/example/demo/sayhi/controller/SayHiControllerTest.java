package com.example.demo.sayhi.controller;

import com.example.demo.sayhi.model.SayHiRequest;
import com.example.demo.sayhi.model.SayHiResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SayHiControllerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void sayHi() {
        stubFor(post("/shakeHand")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("shakeHandToMeResponse.json")));

        SayHiRequest sayHiRequest = SayHiRequest.builder()
                .name("ink")
                .sayHiTo("Ink")
                .message("Hello Ink")
                .build();
        String url = String.format("http://localhost:%s/sayHi", port);
        SayHiResponse response = restTemplate.postForObject(url, sayHiRequest, SayHiResponse.class);

        verify(postRequestedFor(urlEqualTo("/shakeHand"))
                .withHeader("Content-Type", equalTo("application/json")));
        assertFalse(response.isShaked());
    }
}