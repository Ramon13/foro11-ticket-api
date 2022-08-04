package br.com.javamoon.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentInputDTO {

    @NotBlank(message = "{comment.message.notblank}")
    @Size(min = 8, max = 1000 * 10, message = "{comment.message.size}")
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "CommentInputDTO [message=" + message + "]";
	}
}
