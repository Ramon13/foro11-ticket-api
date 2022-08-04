package br.com.javamoon.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.javamoon.core.validation.TicketRepositoryQueries;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.filter.TicketFilter;

@Repository
public class TicketRepositoryImpl implements TicketRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Ticket> filter(TicketFilter ticketFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Ticket> criteria = builder.createQuery(Ticket.class);
		Root<Ticket> root = criteria.from(Ticket.class);
		root.fetch("createdBy");
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (Objects.nonNull(ticketFilter.getId())) {
			predicates.add(builder.equal(root.get("id"), ticketFilter.getId()));
		}
		
		if (Objects.nonNull(ticketFilter.getStatus())) {
			predicates.add(builder.equal(root.get("status"), ticketFilter.getStatus()));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Ticket> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
