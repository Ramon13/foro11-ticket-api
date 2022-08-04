package br.com.javamoon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.domain.service.TicketStatusChangeService;

@RestController
@RequestMapping("/tickets/{ticketId}/status")
public class TicketStatusChangeController {

	@Autowired
	private TicketStatusChangeService ticketStatusChangeService;
	
	@PutMapping("/pending")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void toPendingStatus(@PathVariable Long ticketId) {
		ticketStatusChangeService.updateToPending(ticketId);
	}
	
	@PutMapping("/closed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void toClosedStatus(@PathVariable Long ticketId) {
		ticketStatusChangeService.updateToClosed(ticketId);
	}
	
	@PutMapping("/not-listed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void toNotListedStatus(@PathVariable Long ticketId) {
		ticketStatusChangeService.updateToNotListed(ticketId);
	}
}
