package br.com.flyway;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	private static final ValidatorFactory factory;

	private static final Validator validator;

	static {
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static <T>  void valid(T entity) {

		Set<ConstraintViolation<T>> constraints = validator.validate(entity);
		
		if (!(constraints == null || constraints.isEmpty())) {
			throw new ConstraintViolationException(constraints);
		}
	}

}
