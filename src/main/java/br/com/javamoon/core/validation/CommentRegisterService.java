package br.com.javamoon.core.validation;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javamoon.domain.exception.ResourceNotFoundException;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.CommentImage;
import br.com.javamoon.domain.repository.CommentRepository;

@Service
public class CommentRegisterService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional
	public Comment save(Comment comment) {
		comment.setCreatedAt( OffsetDateTime.now() );
		
		return commentRepository.save(comment);
	}
	
	public CommentImage getImage(Long ticketId, Long commentId, Long imageId) {
		Comment comment = findOrElseThrow(ticketId, commentId);
		
		return commentRepository.findImageById(comment.getId(), imageId)
			.orElseThrow(() -> new ResourceNotFoundException("Image with id: " + imageId + " not found in comment: " + commentId));
	}
	
	public Comment findOrElseThrow(Long ticketId, Long commentId) {
		return commentRepository.findById(ticketId, commentId)
			.orElseThrow(() -> new ResourceNotFoundException(
					String.format("Comment with id: %d not found on ticket with id: %d", commentId, ticketId))
			);
	}
}
