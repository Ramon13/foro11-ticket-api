package br.com.javamoon.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.javamoon.domain.enumeration.TicketStatusDescription;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.model.TicketStatus;
import br.com.javamoon.domain.model.User;
import br.com.javamoon.domain.repository.CommentRepository;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.UserRepository;

@Service
public class TicketRegisterService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	private final TicketStatus PENDING = TicketStatus.fromStatus(TicketStatusDescription.P); 
	
	@Transactional
	public Ticket save(Ticket ticket) {
		User loggedUser = getLoggedUser();
		
		ticket.setStatus(PENDING);
		ticket.setCreatedBy(loggedUser);
		ticket.setCreatedAt(OffsetDateTime.now());
		
		ticketRepository.save(ticket);
		saveComments(ticket.getComments(), loggedUser, ticket);
		
		return ticket;
	}
	
	@Transactional
	private void saveComments(List<Comment> comments, User author, Ticket ticket) {
		for (Comment comment : comments) {
			comment.setCreatedBy(author);
			comment.setCreatedAt( OffsetDateTime.now() );
			comment.setTicket(ticket);
			
			commentRepository.save(comment);
		}
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
