package br.com.javamoon.domain.repository.filter;

import br.com.javamoon.core.validation.Status;

public class TicketFilter {

	private Long id;
	
	private String title;
	
	@Status(message = "{ticket.status.name.invalid}", nullable = true)
	private String status;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
