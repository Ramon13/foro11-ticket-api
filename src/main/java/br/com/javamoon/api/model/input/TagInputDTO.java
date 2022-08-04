package br.com.javamoon.api.model.input;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TagInputDTO {

	@NotNull(message = "{tag.id.notnull}")
    @Positive(message = "{tag.id.positive}")
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "TagInputDTO [id=" + id + "]";
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
		TagInputDTO other = (TagInputDTO) obj;
		return Objects.equals(id, other.id);
	}
}
