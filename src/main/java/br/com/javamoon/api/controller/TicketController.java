package br.com.javamoon.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	public Ticket find(@PathVariable Long ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(
			() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						"Ticket not found with id: " + ticketId
					)
		);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket add(@RequestBody @Valid Ticket ticket) {
		return ticketRegisterService.save(ticket);
	}
}
