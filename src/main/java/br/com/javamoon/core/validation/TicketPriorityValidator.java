package br.com.javamoon.core.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javamoon.domain.enumeration.TicketPriority;

public class TicketPriorityValidator implements ConstraintValidator<Priority, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Objects.nonNull(value)) {
			try {
				TicketPriority.fromString(value);
				return true;
				
			}catch(IllegalArgumentException e) {
				return false;
			}
		}
		
		return true;
	}

}
