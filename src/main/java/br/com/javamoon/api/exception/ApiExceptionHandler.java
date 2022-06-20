package br.com.javamoon.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.javamoon.api.exception.Problem.Field;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		BindingResult bindingResult = ex.getBindingResult();
		List<Field> problemFields = bindingResult.getFieldErrors().stream()
			.map(fieldError -> {
				return new Problem.Field(
						fieldError.getField(),
						messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
			})
			.collect(Collectors.toList());
		
		Problem problem = new Problem(status.value(), problemFields);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
}
