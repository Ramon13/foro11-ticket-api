package br.com.javamoon.api.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	public ResourceNotFoundException(Long id) {
		this(String.format("Resource not found with id: %d", id));
	}
}
