package br.com.javamoon.domain.enumeration;

public enum TicketStatusDescription {
	P("In Progress"),
	C("Closed"),
	N("Not Listed");
	
	String description;
	
	TicketStatusDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Character getStatus() {
		return this.toString().charAt(0);
	}
	
	public static TicketStatusDescription fromCharacter(Character status) {
		return TicketStatusDescription.valueOf( status.toString() );
	}
}
