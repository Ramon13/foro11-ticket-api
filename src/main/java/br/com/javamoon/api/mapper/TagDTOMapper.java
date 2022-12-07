package br.com.javamoon.api.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javamoon.api.model.TagDTO;
import br.com.javamoon.api.model.input.TagInputDTO;
import br.com.javamoon.domain.model.Tag;

@Component
public class TagDTOMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Collection<TagDTO> toCollectionDTO(Collection<Tag> tags) {
		return tags.stream().map(tag -> toDTO(tag)).collect(Collectors.toList());
	}
	
	public TagDTO toDTO(Tag tag) {
		return modelMapper.map(tag, TagDTO.class);
	}
	
	public Tag toDomainObject(TagInputDTO tagDTO) {
		return modelMapper.map(tagDTO, Tag.class);
	}
}
