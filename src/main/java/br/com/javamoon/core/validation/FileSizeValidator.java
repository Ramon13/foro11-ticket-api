package br.com.javamoon.core.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile[]>{

	private DataSize maxSize;
	
	@Override
	public void initialize(FileSize constraintAnnotation) {
		this.maxSize = DataSize.parse(constraintAnnotation.max());
	}
	
	@Override
	public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
		if (Objects.nonNull(files)) {
			for (MultipartFile file : files) {
				if (file.getSize() > this.maxSize.toBytes()) {
					return false;
				}
			}
		}
			
		return true;
 	}

}
