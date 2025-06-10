package co.edu.unicauca.infra.validation.strategies;

import co.edu.unicauca.interfaces.IValidationStrategy;

public class EmailValidationStrategy implements IValidationStrategy {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    @Override
    public boolean isValid(String input) {
        return input != null && input.matches(EMAIL_REGEX);
    }

    @Override
    public String getErrorMessage() {
        return "El campo Email no tiene un formato válido.";
    }
}