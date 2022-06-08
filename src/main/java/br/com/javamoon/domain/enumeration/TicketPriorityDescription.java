package br.com.javamoon.domain.enumeration;

public enum TicketPriorityDescription {
	H("High"),
	N("Normal"),
	L("Low");
	
	String description;
	
	TicketPriorityDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static TicketPriorityDescription fromCharacter(Character priority) {
		return TicketPriorityDescription.valueOf( priority.toString() );
	}
}
