package br.com.javamoon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javamoon.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{}
