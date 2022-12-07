package br.com.javamoon.api.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javamoon.api.model.CommentImageDTO;
import br.com.javamoon.domain.model.CommentImage;

@Component
public class CommentImageDTOMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Collection<CommentImageDTO> toCollectionDTO(Collection<CommentImage> images) {
		return images.stream().map(image -> toDTO(image)).collect(Collectors.toList());
	}
	
	public CommentImageDTO toDTO(CommentImage ticketImage) {
		return modelMapper.map(ticketImage, CommentImageDTO.class);
	}
}
