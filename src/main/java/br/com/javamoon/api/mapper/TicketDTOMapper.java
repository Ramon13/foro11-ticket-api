package br.com.javamoon.api.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javamoon.api.model.TicketDTO;
import br.com.javamoon.api.model.input.TicketMultipartInput;
import br.com.javamoon.api.model.update.TicketUpdateDTO;
import br.com.javamoon.domain.enumeration.TicketPriority;
import br.com.javamoon.domain.model.Tag;
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
	
	public Ticket toDomainObject(TicketMultipartInput ticketInput) {
		Ticket ticket = modelMapper.map(ticketInput, Ticket.class);
		
		ticket.setPriority(TicketPriority.fromString(ticketInput.getPriority()));
		
		ticketInput.getTagIds().stream()
			.forEach( tagId -> ticket.getTags().add(new Tag(tagId)) );
		
		return ticket;
	}	
	
	public void copyToDomainObject(TicketUpdateDTO ticketUpdate, Ticket ticket) {
		ticket.setTags(new HashSet<>());
		
		modelMapper.map(ticketUpdate, ticket);
	}
}
