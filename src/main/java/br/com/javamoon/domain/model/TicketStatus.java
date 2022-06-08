package br.com.javamoon.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.javamoon.domain.enumeration.TicketStatusDescription;

@Embeddable
public class TicketStatus {

	@Column(name = "ticket_status")
	private Character name;

	public Character getName() {
		return name;
	}

	public void setName(Character name) {
		this.name = name;
	}
	
	public String getDescription() {
		return TicketStatusDescription.fromCharacter(name).getDescription();
	}
}
