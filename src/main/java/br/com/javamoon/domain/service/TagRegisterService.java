package br.com.javamoon.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javamoon.domain.exception.ResourceNotFoundException;
import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.repository.TagRepository;

@Service
public class TagRegisterService {

	@Autowired
	private TagRepository tagRepository;
	
	@Transactional
	public Tag save(Tag newTag) {
		return tagRepository.save(newTag);
	}
	
	public Tag findOrElseThrow(Long tagId) {
		return tagRepository.findById(tagId)
			.orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + tagId));
	}
}
