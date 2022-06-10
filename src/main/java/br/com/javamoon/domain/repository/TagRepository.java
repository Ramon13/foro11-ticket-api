package br.com.javamoon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javamoon.domain.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
