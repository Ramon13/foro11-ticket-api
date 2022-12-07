package br.com.javamoon.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.filter.TicketFilter;

public interface TicketRepositoryQueries {

	Page<Ticket> filter(TicketFilter ticketFilter, Pageable pageable);
}
