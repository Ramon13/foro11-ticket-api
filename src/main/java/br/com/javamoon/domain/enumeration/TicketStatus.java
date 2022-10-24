package br.com.javamoon.domain.enumeration;

public enum TicketStatus {
	PENDING,
	CLOSED,
	NOT_LISTED;
	
	public static TicketStatus fromString(String status) {
		return TicketStatus.valueOf(status.toUpperCase());
	}
}
