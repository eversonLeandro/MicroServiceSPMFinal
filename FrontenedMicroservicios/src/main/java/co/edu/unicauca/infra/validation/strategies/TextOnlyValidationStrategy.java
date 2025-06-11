package co.edu.unicauca.infra.validation.strategies;

import co.edu.unicauca.interfaces.IValidationStrategy;

public class TextOnlyValidationStrategy implements IValidationStrategy {
    @Override
    public boolean isValid(String input) {
        return input != null && !input.matches(".*\\d.*");
    }

    @Override
    public String getErrorMessage() {
        return "Algunos campos solo acepta entradas Alfabéticas.";
    }
}