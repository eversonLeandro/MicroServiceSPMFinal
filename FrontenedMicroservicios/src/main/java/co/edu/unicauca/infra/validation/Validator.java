package co.edu.unicauca.infra.validation;

import co.edu.unicauca.interfaces.IValidationStrategy;
import java.util.ArrayList;
import java.util.List;

public class Validator implements IValidationStrategy {
    private List<IValidationStrategy> strategies = new ArrayList<>();
    
    public void addStrategy(IValidationStrategy strategy) {
        strategies.add(strategy);
    }
    
    @Override
    public boolean isValid(String input) {
        for (IValidationStrategy strategy : strategies) {
            if (!strategy.isValid(input)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        // Retorna el mensaje del primer error encontrado
        for (IValidationStrategy strategy : strategies) {
            String error = strategy.getErrorMessage();
            if (error != null && !error.isEmpty()) {
                return error;
            }
        }
        return "Error de validación";
    }
}