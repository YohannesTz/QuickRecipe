package com.github.yohannestz.QuickRecipe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EntryController {

    @GetMapping
    public ResponseEntity<Map<String, String>> getRequest () {
        Map<String, String> response = new HashMap<>();
        response.put("status", "running");
        response.put("message", "check out the project on https://github.com/YohannesTz/QuickRecipe");

        return ResponseEntity.ok(response);
    }
}
