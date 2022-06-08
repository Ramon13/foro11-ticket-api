package br.com.javamoon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javamoon.domain.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{}
