package com.rehneo.informationsecurity.lab1.repository;

import com.rehneo.informationsecurity.lab1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}