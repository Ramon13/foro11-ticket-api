package br.com.javamoon.api.controller;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.api.mapper.CommentDTOMapper;
import br.com.javamoon.api.mapper.CommentImageDTOMapper;
import br.com.javamoon.api.model.CommentDTO;
import br.com.javamoon.api.model.CommentImageDTO;
import br.com.javamoon.api.model.input.CommentInputDTO;
import br.com.javamoon.core.validation.CommentRegisterService;
import br.com.javamoon.domain.exception.ResourceNotFoundException;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.CommentImage;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.service.FileStorageService;
import br.com.javamoon.domain.service.TicketRegisterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tickets/{ticketId}/comments")
public class CommentController {

	@Autowired
	private TicketRegisterService ticketRegisterService;
	
	@Autowired
	private CommentRegisterService commentRegisterService;
	
	@Autowired
	private CommentDTOMapper commentMapper;
	
	@Autowired
	private CommentImageDTOMapper imageDTOMapper;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping
	public List<CommentDTO> list(@PathVariable Long ticketId) {
		Ticket ticket = ticketRegisterService.findTicketOrElseThrow(ticketId);
		
		return ticket.getComments()
			.stream()
			.map(comment -> commentMapper.toDTO(comment))
			.collect(Collectors.toList());
	}
	
	@GetMapping("/{commentId}")
	public CommentDTO find(@PathVariable Long ticketId, @PathVariable Long commentId) {
		Comment comment = commentRegisterService.findOrElseThrow(ticketId, commentId);
		
		return commentMapper.toDTO(comment);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentDTO add(@PathVariable Long ticketId, 
			@RequestBody @Valid CommentInputDTO commentInputDTO) {
		Comment comment = commentMapper.toDomainObject(commentInputDTO);
		
		Comment savedComment = ticketRegisterService.saveComment(ticketId, 1L, comment);
		
		return commentMapper.toDTO(savedComment);
	}
	
	@PutMapping("/{commentId}")
	public CommentDTO update(@PathVariable Long ticketId, @PathVariable Long commentId, 
			@RequestBody @Valid CommentInputDTO commentInputDTO) {
		Comment comment = commentRegisterService.findOrElseThrow(ticketId, commentId);
		commentMapper.copyToDomainObject(commentInputDTO, comment);
		
		return commentMapper.toDTO(commentRegisterService.save(comment));
	}
	
	@GetMapping(value = "/{commentId}/images/{imageId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommentImageDTO getImage(@PathVariable Long ticketId, @PathVariable Long commentId, @PathVariable Long imageId) {
		CommentImage image = commentRegisterService.getImage(ticketId, commentId, imageId);
		
		return imageDTOMapper.toDTO(image); 
	}
	
	//TODO: inplement media type validation
	@GetMapping(value = "/{commentId}/images/{imageId}")
	public ResponseEntity<InputStreamResource> getImageStream(@PathVariable Long ticketId, @PathVariable Long commentId, @PathVariable Long imageId) {
		try {
			CommentImage image = commentRegisterService.getImage(ticketId, commentId, imageId);
			
			InputStream inputStream = fileStorageService.retrieve(image.getName());
			MediaType imageMediaType = MediaType.parseMediaType(image.getContentType());
			
			return ResponseEntity.ok()
				.contentType(imageMediaType)
				.body(new InputStreamResource(inputStream));
			
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
