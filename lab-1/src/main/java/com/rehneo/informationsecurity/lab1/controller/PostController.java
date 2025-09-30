package com.rehneo.informationsecurity.lab1.controller;

import com.rehneo.informationsecurity.lab1.dto.PostDto;
import com.rehneo.informationsecurity.lab1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<PostDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
