package br.com.javamoon.api.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.javamoon.api.model.input.TicketMultipartInput;
import br.com.javamoon.api.model.update.TicketUpdateDTO;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.filter.TicketFilter;
import br.com.javamoon.domain.service.TagRegisterService;
import br.com.javamoon.domain.service.TicketRegisterService;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketRegisterService ticketRegisterService;
	
	@Autowired
	private TagRegisterService tagRegisterService;

	@Autowired
	private TicketDTOMapper ticketMapper;
	
	@GetMapping
	public Page<TicketDTO> list(@Valid TicketFilter filters, Pageable pageable) {
		Page<Ticket> ticketsPage = ticketRepository.filter(filters, pageable);
		
		Collection<TicketDTO> ticketsDTO = ticketMapper.toCollectionDTO(ticketsPage.getContent());
		
		return new PageImpl<TicketDTO>((List<TicketDTO>) ticketsDTO, pageable, ticketsPage.getTotalElements());
	}
	
	@GetMapping("/{ticketId}")
	public TicketDTO find(@PathVariable Long ticketId) {
		Ticket ticket = ticketRepository.findById(ticketId)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Ticket not found with id: " + ticketId));
		
		return ticketMapper.toDTO(ticket);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public TicketDTO add(@Valid TicketMultipartInput ticketInput) throws IOException {
		Ticket ticket = ticketMapper.toDomainObject(ticketInput);
		Set<Tag> tags = ticket.getTags().stream()
			.map(tag -> tagRegisterService.findOrElseThrow(tag.getId()))
			.collect(Collectors.toSet());
		ticket.setTags(tags);
		
		Comment comment = new Comment();
		comment.setMessage(ticketInput.getMessage());
		comment.setImages(ticketInput.getImages());
		ticket.getComments().add(comment);
		
		Ticket savedTicket = ticketRegisterService.save(ticket);
		
		return ticketMapper.toDTO(savedTicket);
	}
	
	@PutMapping("/{ticketId}")
	public TicketDTO update(@PathVariable Long ticketId,
			@RequestBody @Valid TicketUpdateDTO ticketUpdateDTO) {
		
		Ticket ticket = ticketRegisterService.findTicketOrElseThrow(ticketId);
		ticketMapper.copyToDomainObject(ticketUpdateDTO, ticket);
		
		//return ticketMapper.toDTO(ticketRegisterService.save(ticket));
		return null;
	}
}
