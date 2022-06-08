package br.com.javamoon.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.TicketRepository;

@Service
public class TicketRegisterService {

	private TicketRepository ticketRepository;
	
	public TicketRegisterService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	
	@Transactional
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
}
