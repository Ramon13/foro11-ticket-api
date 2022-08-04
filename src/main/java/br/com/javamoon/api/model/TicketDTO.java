package br.com.javamoon.api.model;

import java.time.OffsetDateTime;
import java.util.List;

public class TicketDTO {

	private Long id;
	private String title;
	private OffsetDateTime createdAt;
	private String status;
	private String priority;
	private UserDTO createdBy;
	private List<TagDTO> tags;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public UserDTO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDTO createdBy) {
		this.createdBy = createdBy;
	}
	public List<TagDTO> getTags() {
		return tags;
	}
	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}
}
