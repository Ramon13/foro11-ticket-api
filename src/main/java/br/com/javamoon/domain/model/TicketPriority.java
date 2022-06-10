package br.com.javamoon.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.javamoon.domain.enumeration.TicketPriorityDescription;

@Embeddable
public class TicketPriority {

	@Column(name = "ticket_priority")
	private Character name;

	public Character getName() {
		return name;
	}

	public void setName(Character name) {
		this.name = name;
	}		
	
	public String getDescription() {
		return TicketPriorityDescription.fromCharacter(name).getDescription();
	}

	@Override
	public String toString() {
		return "TicketPriority [name=" + name + "]";
	}
}
