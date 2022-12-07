package br.com.javamoon.core.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile[]>{

	private String[] allowed;
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowed = constraintAnnotation.allowed();
	}
	
	@Override
	public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
		if (Objects.nonNull(files)) {
			for (MultipartFile file : files) {
				if (!isAllowed(file.getContentType())) {
					return false;
				}
			}
		}
		
		return true;
	}

	public boolean isAllowed(String fileType) {
		for (String type : allowed) {
			if (type.equals(fileType)) {
				return true;
			}
		}
		
		return false;
	}
}
