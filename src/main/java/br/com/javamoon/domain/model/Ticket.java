package br.com.javamoon.domain.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@Column(name = "created_at")
	private OffsetDateTime createdAt;
	
	@Embedded
	private TicketStatus status;

	@Valid
	@Embedded
	private TicketPriority priority;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "ticket")
	private List<Comment> comments;
	
	@Valid
	@NotEmpty
	@ManyToMany
	@JoinTable(
	    name = "ticket_has_tag",
	    joinColumns = @JoinColumn(name = "ticket_id"),
	    inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;
	
	public Ticket() {}
	
	
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
	
	public TicketStatus getStatus() {
		return status;
	}
	
	public void setStatus(TicketStatus status) {
		this.status = status;
	}
	
	public TicketPriority getPriority() {
		return priority;
	}
	
	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", createdAt=" + createdAt + ", status=" + status
				+ ", priority=" + priority + ", createdBy=" + createdBy + ", comments=" + comments + ", tags=" + tags
				+ "]";
	}
}
