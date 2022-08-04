package br.com.javamoon;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.api.mapper.CommentDTOMapper;
import br.com.javamoon.api.model.CommentDTO;
import br.com.javamoon.api.model.input.CommentInputDTO;
import br.com.javamoon.core.validation.CommentRegisterService;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.service.TicketRegisterService;

@RestController
@RequestMapping("/tickets/{ticketId}/comments")
public class TicketCommentController {

	@Autowired
	private TicketRegisterService ticketRegisterService;
	
	@Autowired
	private CommentRegisterService commentRegisterService;
	
	@Autowired
	private CommentDTOMapper commentMapper;
	
	@GetMapping
	public List<CommentDTO> list(@PathVariable Long ticketId) {
		Ticket ticket = ticketRegisterService.findOrElseThrow(ticketId);
		
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
		
		Comment savedComment = ticketRegisterService.saveComment(ticketId, comment);
		
		return commentMapper.toDTO(savedComment);
	}
	
	@PutMapping("/{commentId}")
	public CommentDTO update(@PathVariable Long ticketId, @PathVariable Long commentId, 
			@RequestBody @Valid CommentInputDTO commentInputDTO) {
		Comment comment = commentRegisterService.findOrElseThrow(ticketId, commentId);
		commentMapper.copyToDomainObject(commentInputDTO, comment);
		
		return commentMapper.toDTO(commentRegisterService.save(comment));
	}
}
