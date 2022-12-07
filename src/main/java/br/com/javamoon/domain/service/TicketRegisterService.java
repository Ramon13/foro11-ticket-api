package br.com.javamoon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.javamoon.core.validation.CommentRegisterService;
import br.com.javamoon.domain.exception.ResourceNotFoundException;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.model.User;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.UserRepository;

@Service
public class TicketRegisterService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRegisterService commentRegisterService;
	
	@Autowired
	private TicketImageRegisterService imageRegisterService;
	
	@Transactional
	public Ticket save(Ticket ticket) {
		User user = findUserOrElseThrow(1L);
		ticket.setCreatedBy(user);
		
		Ticket savedTicket = ticketRepository.save(ticket);
		Comment savedComment = saveComment(savedTicket.getId(), user.getId(), ticket.getComments().get(0));
		
		savedComment.getImages().forEach(image -> {
			image.setComment(savedComment);
			imageRegisterService.save(image, image.getInStream());
		});
		
		return savedTicket;
	}
	 
	@Transactional
	public Comment saveComment(long ticketID, long userID, Comment comment) {
		Ticket ticket = findTicketOrElseThrow(ticketID);
		User user = findUserOrElseThrow(1L);
		
		comment.setCreatedBy(user);
		comment.setTicket(ticket);
		return commentRegisterService.save(comment);
	}

	public Ticket findTicketOrElseThrow(long ticketId) {
		return ticketRepository.findById(ticketId)
			.orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + ticketId));
	}
	
	public User findUserOrElseThrow(long userID) {
		return userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
	}
}
