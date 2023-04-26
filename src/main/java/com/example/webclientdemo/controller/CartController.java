package com.example.webclientdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webclientdemo.externalconnector.WebClientConnector;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class CartController{

    @Autowired
    WebClientConnector webClientConnector;

    @GetMapping("/getairlines")
    public JsonNode getAirlines()
    {
       return webClientConnector.getRequest(null, "https://api.instantwebtools.net/v1/airlines");
    }

    @PostMapping("/postairlines")
    public JsonNode createairLines(@RequestBody JsonNode data)
    {
        return webClientConnector.postRequest("https://api.instantwebtools.net/v1/airlines", null, data);
    }
}