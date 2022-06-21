package br.com.javamoon.core.validation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TicketPriorityValidator.class })
public @interface Priority {

	String message() default "Invalid ticket priority";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}
