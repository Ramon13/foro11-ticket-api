package br.com.javamoon.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TagInputDTO {

	@NotBlank(message = "{tag.name.notblank}")
    @Size(min = 3, max = 20, message = "{tag.name.size}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
