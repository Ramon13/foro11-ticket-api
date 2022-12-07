package br.com.javamoon.api.controller;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.api.mapper.TagDTOMapper;
import br.com.javamoon.api.model.TagDTO;
import br.com.javamoon.api.model.input.TagInputDTO;
import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.repository.TagRepository;
import br.com.javamoon.domain.service.TagRegisterService;
	
@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private TagRegisterService tagRegisterService;
	
	@Autowired
	private TagDTOMapper mapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Collection<TagDTO>> list() {
		return ResponseEntity
				.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(mapper.toCollectionDTO(tagRepository.findAll()));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TagDTO add(@RequestBody @Valid TagInputDTO tagInputDTO) {
		Tag newTag = mapper.toDomainObject(tagInputDTO);
		
		return mapper.toDTO( tagRegisterService.save(newTag) );
	}
}
