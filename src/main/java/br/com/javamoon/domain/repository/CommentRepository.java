package br.com.javamoon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javamoon.domain.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{}
