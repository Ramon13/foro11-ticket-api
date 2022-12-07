package br.com.javamoon.infrastructure.repository;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.javamoon.domain.enumeration.TicketStatus;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.repository.TicketRepositoryQueries;
import br.com.javamoon.domain.repository.filter.TicketFilter;

@Repository
public class TicketRepositoryImpl implements TicketRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Ticket> filter(TicketFilter ticketFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		List<Ticket> content = list(builder, ticketFilter, pageable);
		Long count = count(builder, ticketFilter, pageable);
		
		return new PageImpl<Ticket>(content, pageable, count);
	}

	private List<Ticket> list(CriteriaBuilder builder, TicketFilter ticketFilter, Pageable pageable) {
		CriteriaQuery<Ticket> criteria = builder.createQuery(Ticket.class);
		Root<Ticket> root = criteria.from(Ticket.class);
		root.fetch("createdBy");
		
		getPredicates(builder, criteria, root, ticketFilter);
		
		TypedQuery<Ticket> query = manager.createQuery(criteria);
		query.setMaxResults(pageable.getPageSize());
		query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
		return query.getResultList();
	}

	private Long count(CriteriaBuilder builder, TicketFilter ticketFilter, Pageable pageable) {
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Ticket> root = criteria.from(Ticket.class);
		criteria.select(builder.count(root));
		
		getPredicates(builder, criteria, root, ticketFilter);
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private void getPredicates(CriteriaBuilder builder, @SuppressWarnings("rawtypes") CriteriaQuery criteria, Root<Ticket> root,
			TicketFilter ticketFilter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (Objects.nonNull(ticketFilter.getTitle())) {
			predicates.add(builder.like(
				builder.lower( root.get("title") ), 
				"%" + ticketFilter.getTitle().toLowerCase() + "%"
			));
		}
		
		if (Objects.nonNull(ticketFilter.getStatus())) {
			TicketStatus status = TicketStatus.fromString(ticketFilter.getStatus());
			predicates.add(builder.equal(root.get("status"), status));
		}
		
		if (Objects.nonNull(ticketFilter.getPriority())) {
			predicates.add(builder.equal(root.get("priority"), ticketFilter.getPriority()	));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
	}
}
