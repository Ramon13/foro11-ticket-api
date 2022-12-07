package br.com.javamoon.unit.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.javamoon.core.validation.CommentRegisterService;
import br.com.javamoon.domain.exception.ResourceNotFoundException;
import br.com.javamoon.domain.model.Comment;
import br.com.javamoon.domain.model.Ticket;
import br.com.javamoon.domain.model.User;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.repository.UserRepository;
import br.com.javamoon.domain.service.TicketRegisterService;

@ExtendWith(MockitoExtension.class)
public class TicketRegisterServiceUnitTest {
		
	@Mock
	private TicketRepository ticketRepository;
	
	@Mock
	private CommentRegisterService commentService;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private TicketRegisterService service;
	
	@Test
	void saveComment() {
		Long ticketId = 1L;
		long userID = 1L;
		Comment comment = new Comment();
		
		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(getValidTicket(ticketId)));
		Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(new User()));
		Mockito.when(commentService.save(comment)).thenReturn(getValidComment(ticketId));
		Comment savedComment = service.saveComment(ticketId, userID, comment);
		
		assertNotNull(savedComment);
		assertEquals(1L, savedComment.getId());
		assertEquals(ticketId, savedComment.getTicket().getId());
	}
	
	@Test
	void saveCommentWithNotExistentTicket() {
		Long ticketId = 1L;
		long userID = 1L;
		String errorMessage = "Ticket not found with id: " + ticketId;
		
		Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.empty());
		
		var exception = assertThrows(ResourceNotFoundException.class, () -> service.saveComment(ticketId, userID, new Comment()));
		assertEquals(errorMessage, exception.getMessage());
	}
			
	private Ticket getValidTicket(Long id) {
		Ticket ticket = new Ticket();
		ticket.setId(id);
		
		return ticket;
	}
	
	private Comment getValidComment(Long ticketId) {
		Comment comment = new Comment();
		comment.setId(1L);
		comment.setTicket(getValidTicket(ticketId));
		return comment;
	}
}
