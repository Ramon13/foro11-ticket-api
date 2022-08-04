package br.com.javamoon.domain.service;

import java.time.OffsetDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.javamoon.api.exception.ResourceNotFoundException;
import br.com.javamoon.core.validation.CommentRegisterService;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.model.User;
import br.com.javamoon.domain.repository.TagRepository;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.UserRepository;

@Service
public class TicketRegisterService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRegisterService commentRegisterService;
	
	@Transactional
	public Ticket save(Ticket ticket) {
		validateTags(ticket.getTags());
		
		ticket.setCreatedBy(getLoggedUser());
		ticket.setCreatedAt(OffsetDateTime.now());
		
		return ticketRepository.save(ticket);
	}
	
	@Transactional
	public Comment saveComment(Long ticketId, Comment comment) {
		Ticket ticket = findOrElseThrow(ticketId);
		
		comment.setCreatedBy(getLoggedUser());
		comment.setTicket(ticket);
		commentRegisterService.save(comment);
		
		return comment;
	}
	
	public Ticket findOrElseThrow(Long ticketId) {
		return ticketRepository.findById(ticketId)
			.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));
	}
	
	private void validateTags(Collection<Tag> tags) throws ResourceNotFoundException{
		tags.stream().forEach(tag -> findTagOrElseThrow(tag.getId()));
	}
	
	private Tag findTagOrElseThrow(Long tagId) {
		return tagRepository.findById(tagId)
			.orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + tagId));
	}
	

	/**
	 * TODO: replace by a real user account in production
	 * Mock a simple user. Development only
	 */
	private User getLoggedUser() {
		return userRepository.findById(1L).orElseThrow(
				() -> new IllegalStateException()
		);
	}
}
