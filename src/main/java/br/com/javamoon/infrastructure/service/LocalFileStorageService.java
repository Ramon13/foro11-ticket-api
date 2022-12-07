package br.com.javamoon.infrastructure.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.com.javamoon.domain.exception.StorageException;
import br.com.javamoon.domain.service.FileStorageService;

@Service
public class LocalFileStorageService implements FileStorageService {

	@Value("${application.storage.local.images}")
	private Path storagePath;
	
	@Override
	public void store(NewFile newFile) {
		try {
			Path filePath = getFilePath(newFile.getName());
			FileCopyUtils.copy(newFile.getInStream(), Files.newOutputStream(filePath));
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}
	
	@Override
	public InputStream retrieve(String filename) {
		try {
			Path filePath = getFilePath(filename);
			return Files.newInputStream(filePath);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private Path getFilePath(String fileName) {
		return storagePath.resolve(fileName);
	}
}
