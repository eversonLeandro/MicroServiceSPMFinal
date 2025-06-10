package co.edu.unicauca.infra.validation.strategies;

import co.edu.unicauca.interfaces.IValidationStrategy;

public class NotEmptyAndNotDefaultStrategy implements IValidationStrategy {
    private String defaultValue;
    private String fieldName;
    
    public NotEmptyAndNotDefaultStrategy(String defaultValue, String fieldName) {
        this.defaultValue = defaultValue;
        this.fieldName = fieldName;
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.trim().isEmpty() && !input.equalsIgnoreCase(defaultValue);
    }

    @Override
    public String getErrorMessage() {
        return "Algun campo esta vacio o tiene formato Incorrecto";
    }
}