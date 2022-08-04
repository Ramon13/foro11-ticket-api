package br.com.javamoon.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.javamoon.core.validation.TicketRepositoryQueries;
import br.com.javamoon.domain.enumeration.TicketStatus;
import br.com.javamoon.domain.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryQueries{
	
	@Query("from Ticket t join fetch t.createdBy")
	List<Ticket> findAllTickets();
	
	List<Ticket> findByStatus(TicketStatus status);
}
