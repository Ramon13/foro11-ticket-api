package br.com.javamoon.api.model;

import java.time.OffsetDateTime;

public class CommentDTO {

	private Long id;
	private String message;
	private OffsetDateTime createdAt;
	private UserDTO createdBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public UserDTO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}
	
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", message=" + message + ", createdAt=" + createdAt + ", createdBy=" + createdBy
				+ "]";
	}
}
