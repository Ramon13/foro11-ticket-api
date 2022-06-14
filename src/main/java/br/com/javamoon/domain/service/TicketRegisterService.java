package br.com.javamoon.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.javamoon.domain.enumeration.TicketStatusDescription;
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
	
	
	@Transactional
	public Ticket save(Ticket ticket) {
		ticket.setStatus(TicketStatus.fromStatus(TicketStatusDescription.P));
		ticket.setCreatedBy(getLoggedUser());
		ticket.setCreatedAt(OffsetDateTime.now());
		
		ticketRepository.save(ticket);
		
		ticket.getComments().forEach(comment -> {
			comment.setCreatedBy(getLoggedUser());
			comment.setCreatedAt(OffsetDateTime.now());
			comment.setTicket(ticket);
			commentRepository.save(comment);
		});
		
		System.out.println(ticket);
		
		return ticket;
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
