package co.edu.unicauca.infra.validation.strategies;

import co.edu.unicauca.interfaces.IValidationStrategy;
import java.math.BigDecimal;

public class NumericDecimalValidationStrategy implements IValidationStrategy {
    @Override
    public boolean isValid(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            new BigDecimal(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Debe ingresar un valor numérico válido";
    }
}