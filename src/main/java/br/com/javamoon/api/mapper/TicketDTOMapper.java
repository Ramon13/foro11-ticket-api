package br.com.javamoon.api.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javamoon.api.model.TicketDTO;
import br.com.javamoon.api.model.input.TicketInputDTO;
import br.com.javamoon.api.model.update.TicketUpdateDTO;
import br.com.javamoon.domain.model.Ticket;

@Component
public class TicketDTOMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Collection<TicketDTO> toCollectionDTO(Collection<Ticket> tickets) {
		return tickets
				.stream()
				.map(ticket -> toDTO(ticket))
				.collect(Collectors.toList());
	}
	
	public TicketDTO toDTO(Ticket ticket) {
		return modelMapper.map(ticket, TicketDTO.class);
	}
	
	public Ticket toDomainObject(TicketInputDTO ticketInput) {
		return modelMapper.map(ticketInput, Ticket.class);
	}
	
	public void copyToDomainObject(TicketUpdateDTO ticketUpdate, Ticket ticket) {
		ticket.setTags(new HashSet<>());
		
		modelMapper.map(ticketUpdate, ticket);
	}
}
