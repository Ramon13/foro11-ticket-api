package br.com.javamoon.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javamoon.domain.enumeration.TicketPriorityDescription;

public class TicketPriorityValidator implements ConstraintValidator<Priority, Character>{

	@Override
	public boolean isValid(Character value, ConstraintValidatorContext context) {
		if (value != null) {
			try {
				TicketPriorityDescription.fromCharacter(value);
				return true;
				
			}catch(IllegalArgumentException e) {
				return false;
			}
		}
		
		return false;
	}

}
