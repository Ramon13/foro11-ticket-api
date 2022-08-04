package br.com.javamoon.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javamoon.api.model.CommentDTO;
import br.com.javamoon.api.model.input.CommentInputDTO;
import br.com.javamoon.domain.model.Comment;

@Component
public class CommentDTOMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Comment toDomainObject(CommentInputDTO commentInput) {
		return modelMapper.map(commentInput, Comment.class);
	}
	
	public CommentDTO toDTO(Comment comment) {
		return modelMapper.map(comment, CommentDTO.class);
	}
	
	public void copyToDomainObject(CommentInputDTO commentInputDTO, Comment comment) {
		modelMapper.map(commentInputDTO, comment);
	}
}
