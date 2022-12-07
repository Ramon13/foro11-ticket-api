package br.com.javamoon.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javamoon.domain.model.Ticket;

@Service
public class TicketStatusChangeService {

	@Autowired
	private TicketRegisterService ticketRegisterService;
	
	@Transactional
	public void updateToPending(Long ticketId) {
		Ticket ticket = ticketRegisterService.findTicketOrElseThrow(ticketId);
		ticket.toPendingStatus();
	}
	
	@Transactional
	public void updateToClosed(Long ticketId) {
		Ticket ticket = ticketRegisterService.findTicketOrElseThrow(ticketId);
		ticket.toClosedStatus();
	}
	
	@Transactional
	public void updateToNotListed(Long ticketId) {
		Ticket ticket = ticketRegisterService.findTicketOrElseThrow(ticketId);
		ticket.toNotListedStatus();
	}
}
