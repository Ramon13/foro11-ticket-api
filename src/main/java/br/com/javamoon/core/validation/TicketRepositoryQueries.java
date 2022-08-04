package br.com.javamoon.core.validation;

import java.util.List;

import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.filter.TicketFilter;

public interface TicketRepositoryQueries {

	List<Ticket> filter(TicketFilter ticketFilter);
}
