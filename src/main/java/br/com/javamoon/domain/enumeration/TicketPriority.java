package br.com.javamoon.domain.enumeration;

public enum TicketPriority {
	HIGH,
	NORMAL,
	LOW;
	
	public static TicketPriority fromString(String priority) {
		return TicketPriority.valueOf(priority.toUpperCase());
	}
}
