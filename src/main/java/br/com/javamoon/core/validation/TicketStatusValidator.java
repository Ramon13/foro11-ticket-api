package br.com.javamoon.core.validation;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javamoon.domain.enumeration.TicketStatus;

public class TicketStatusValidator implements ConstraintValidator<Status, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Objects.nonNull(value)) {
			try {
				TicketStatus.fromString(value);
				return true;
				
			}catch(IllegalArgumentException e) {
				return false;
			}
		}

		return true;
	}

	public static boolean isValid(String status) {
		return new TicketStatusValidator().isValid(status, null);
	}
}
