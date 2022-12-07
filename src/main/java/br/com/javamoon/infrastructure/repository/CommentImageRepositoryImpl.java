package br.com.javamoon.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.javamoon.domain.model.CommentImage;
import br.com.javamoon.domain.repository.TicketImageRepositoryQueries;

public class CommentImageRepositoryImpl implements TicketImageRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public CommentImage save(CommentImage image) {
		return manager.merge(image);
	}

}
