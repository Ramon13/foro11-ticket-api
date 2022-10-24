package br.com.javamoon.api.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.javamoon.api.mapper.TicketDTOMapper;
import br.com.javamoon.api.model.TicketDTO;
import br.com.javamoon.api.model.input.TicketInputDTO;
import br.com.javamoon.api.model.update.TicketUpdateDTO;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.filter.TicketFilter;
import br.com.javamoon.domain.service.TicketRegisterService;

@CrossOrigin
@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketRegisterService ticketRegisterService;

	@Autowired
	private TicketDTOMapper ticketMapper;
	
	@GetMapping
	public Collection<TicketDTO> list(@Valid TicketFilter filters) {
		List<Ticket> tickets = ticketRepository.filter(filters);
		
		return ticketMapper.toCollectionDTO(tickets);
	}
	
	@GetMapping("/{ticketId}")
	public TicketDTO find(@PathVariable Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ticket not found with id: " + ticketId));
		
		return ticketMapper.toDTO(ticket);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TicketDTO add(@RequestBody @Valid TicketInputDTO ticketInput) {
		Ticket ticket = ticketMapper.toDomainObject(ticketInput);
		
		Ticket savedTicket = ticketRegisterService.save(ticket);
		
		return ticketMapper.toDTO(savedTicket);
	}
	
	@PutMapping("/{ticketId}")
	public TicketDTO update(@PathVariable Long ticketId,
			@RequestBody @Valid TicketUpdateDTO ticketUpdateDTO) {
		
		Ticket ticket = ticketRegisterService.findOrElseThrow(ticketId);
		ticketMapper.copyToDomainObject(ticketUpdateDTO, ticket);
		
		return ticketMapper.toDTO(ticketRegisterService.save(ticket));
	}
}
