package br.com.javamoon.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.repository.TagRepository;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Tag> list() {
		return tagRepository.findAll();
	}
}
