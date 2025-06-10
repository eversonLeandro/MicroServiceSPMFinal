package co.edu.unicauca.infra.validation;

import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.interfaces.IValidationStrategy;

public class ValidationContext {

    private IValidationStrategy strategy;

    public ValidationContext(IValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeValidation(String input) {
        boolean isValid = strategy.isValid(input);
        if (!isValid) {
            Messages.showMessageDialog(strategy.getErrorMessage(), "Error de Validación");
        }
        return isValid;
    }

    public void showErrorIfInvalid(String input) {
        if (!executeValidation(input)) {
            Messages.showMessageDialog(strategy.getErrorMessage(), "Error de Validación");
        }
    }

    public void setStrategy(IValidationStrategy strategy) {
        this.strategy = strategy;
    }
}
