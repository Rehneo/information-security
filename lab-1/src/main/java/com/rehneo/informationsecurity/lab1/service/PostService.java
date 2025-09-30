package com.rehneo.informationsecurity.lab1.service;

import com.rehneo.informationsecurity.lab1.dto.PostDto;
import com.rehneo.informationsecurity.lab1.mapper.PostMapper;
import com.rehneo.informationsecurity.lab1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostMapper mapper;

    public List<PostDto> findAll() {
        return repository.findAll().stream().map(mapper::map).toList();
    }
}
