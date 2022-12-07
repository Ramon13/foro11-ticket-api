package br.com.javamoon.api.model.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.javamoon.core.validation.FileContentType;
import br.com.javamoon.core.validation.FileSize;
import br.com.javamoon.core.validation.Priority;
import br.com.javamoon.domain.model.CommentImage;

public class TicketMultipartInput {

	@NotBlank(message = "{ticket.title.notblank}")
    @Size(min = 8, max = 127, message = "{ticket.title.size}")
	private String title;
	
	@NotBlank(message = "{comment.message.notblank}")
	@Size(min = 8, max = 10)
	private String message;
	
	@NotNull(message = "{ticket.priority.name.notnull}")
    @Priority(message = "{ticket.priority.name.invalid}")
	private String priority;
    
    @Valid
    @NotEmpty(message = "{ticket.tags.notempty}")
    @JsonProperty("tags")
	private List<Long> tagIds;
	
    @FileSize(max = "1MB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, "image/webp" })
    private MultipartFile[] files;
    
    public List<CommentImage> getImages() throws IOException {
    	List<CommentImage> images = new ArrayList<>();
    	
    	if (Objects.nonNull(files)) {
	    	for (var file : files) {
	    		CommentImage image = new CommentImage();
				image.setName(file.getOriginalFilename());
				image.setContentType(file.getContentType());
				image.setSize(file.getSize());
				image.setInStream(file.getInputStream());
				images.add(image);
	    	}
    	}
    	
    	return images;
    }
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<Long> getTagIds() {
		return tagIds;
	}
	
	public void setTags(List<Long> tagIds) {
		this.tagIds = tagIds;
	}
	
	public MultipartFile[] getFiles() {
		return files;
	}
	
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
}
