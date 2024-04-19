package com.example.simplejwt.api.controller;

import com.example.simplejwt.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestController {

    private final JwtTokenService service;

    @Autowired
    public TestController(JwtTokenService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> testEndpoint(HttpServletRequest httpServletRequest){

        int userId = service.getUserIdFromToken(httpServletRequest);

        return new ResponseEntity<>( "Hello World!" +  userId, HttpStatus.OK);
    }
}
