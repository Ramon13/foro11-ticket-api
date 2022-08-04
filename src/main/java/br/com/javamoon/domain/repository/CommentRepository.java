package br.com.javamoon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.javamoon.domain.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query("from Comment where ticket.id = :ticket and id = :comment")
	Optional<Comment> findById(@Param("ticket") Long ticketId, @Param("comment") Long commentId);
}
