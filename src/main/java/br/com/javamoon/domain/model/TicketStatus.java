package br.com.javamoon.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import br.com.javamoon.domain.enumeration.TicketStatusDescription;

@Embeddable
public class TicketStatus {

	@Column(name = "ticket_status")
	private Character name;

	public TicketStatus(Character name) {
		this.name = name;
	}
	
	public TicketStatus() {}
	
	public Character getName() {
		return name;
	}

	public void setName(Character name) {
		this.name = name;
	}
	
	public String getDescription() {
		return TicketStatusDescription.fromCharacter(name).getDescription();
	}
	
	public static TicketStatus fromStatus(TicketStatusDescription status) {
		return new TicketStatus(status.getStatus());
	}

	@Override
	public String toString() {
		return "TicketStatus [name=" + name + "]";
	}
}
