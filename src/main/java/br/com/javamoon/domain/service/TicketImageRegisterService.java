package br.com.javamoon.domain.service;

import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javamoon.domain.model.CommentImage;
import br.com.javamoon.domain.repository.TicketRepository;
import br.com.javamoon.domain.service.FileStorageService.NewFile;

@Service
public class TicketImageRegisterService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Transactional
	public CommentImage save(CommentImage image, InputStream in) {
		String newFileName = fileStorageService.generateFileName(image.getName());
		image.setName(newFileName);
		
		CommentImage savedImage = ticketRepository.save(image);
		ticketRepository.flush();
		
		
		NewFile file = NewFile.getBuilder()
			.name(savedImage.getName())
			.inStream(in)
			.build();
		fileStorageService.store(file);
		
		return savedImage;
	}
}
