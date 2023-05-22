package com.example.springbootdocker.controller;

import com.example.springbootdocker.dto.YourResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    @PostMapping("/api/your-name/{name}")
    public ResponseEntity<?> YourName(@PathVariable String name) {
        YourResponse responseObject = new YourResponse(name);
        return ResponseEntity.ok(responseObject);
    }
}
