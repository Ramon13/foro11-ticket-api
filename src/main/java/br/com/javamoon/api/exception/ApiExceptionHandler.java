package br.com.javamoon.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.javamoon.api.exception.Problem.Field;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private final String RESOURCE_NOT_FOUND = "Recurso não encontrado.";
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request
	) {
		return handleValidationInternal(ex, status, request, ex.getBindingResult());
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		return handleValidationInternal(ex, status, request, ex.getBindingResult());
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpStatus status,
			WebRequest request, BindingResult bindingResult) {
		List<Field> problemFields = bindingResult.getFieldErrors().stream()
			.map(fieldError -> {
				return new Problem.Field(
						fieldError.getField(),
						messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
			})
			.collect(Collectors.toList());
		
		Problem problem = Problem.newBuilder()
			.status(status.value())
			.fields(problemFields)
			.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String title = RESOURCE_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem problem = Problem.newBuilder()
			.status(status.value())
			.title(title)
			.detail(detail)
			.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
}
