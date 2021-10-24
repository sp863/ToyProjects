package utils;

public class ValidatorException extends IllegalArgumentException {

	public ValidatorException(String errorMessage) {
		System.out.println(errorMessage);
	}
}
