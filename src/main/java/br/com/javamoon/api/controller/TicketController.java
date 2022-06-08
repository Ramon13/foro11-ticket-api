package br.com.javamoon.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.service.TicketRegisterService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketRegisterService ticketRegisterService;

	@GetMapping
	public List<Ticket> list() {
		return ticketRepository.findAll();
	}
	
	@GetMapping("/{ticketId}")
	public ResponseEntity<Ticket> find(@PathVariable Long ticketId) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		
		if (ticket.isPresent()) {
			return ResponseEntity.ok(ticket.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket add(@RequestBody Ticket ticket) {
		System.out.println(ticket.getTitle());
		return null;
		//return ticketRegisterService.save(ticket);
	}
}
