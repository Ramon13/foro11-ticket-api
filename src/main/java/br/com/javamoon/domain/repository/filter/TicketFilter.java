package br.com.javamoon.domain.repository.filter;

import br.com.javamoon.domain.enumeration.TicketStatus;

public class TicketFilter {

	private Long id;
	private TicketStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TicketStatus getStatus() {
		return status;
	}
	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TicketFilter [id=" + id + ", status=" + status + "]";
	}
}
