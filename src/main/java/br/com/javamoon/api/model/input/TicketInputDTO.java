package br.com.javamoon.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.javamoon.core.validation.Priority;

public class TicketInputDTO {

	@NotBlank(message = "{ticket.title.notblank}")
    @Size(min = 8, max = 127, message = "{ticket.title.size}")
	private String title;
    
	@NotNull(message = "{ticket.priority.name.notnull}")
    @Priority(message = "{ticket.priority.name.invalid}")
	private String priority;
    
    @Valid
    @NotEmpty(message = "{ticket.tags.notempty}")
	private List<TagInputDTO> tags;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<TagInputDTO> getTags() {
		return tags;
	}
	public void setTags(List<TagInputDTO> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return "TicketInputDTO [title=" + title + ", priority=" + priority + ", tags=" + tags + "]";
	}
}
