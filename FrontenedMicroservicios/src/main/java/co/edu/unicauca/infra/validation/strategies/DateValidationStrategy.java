package co.edu.unicauca.infra.validation.strategies;

import co.edu.unicauca.interfaces.IValidationStrategy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidationStrategy implements IValidationStrategy {
    private String dateFormat;
    
    public DateValidationStrategy(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    
    @Override
    public boolean isValid(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String getErrorMessage() {
        return "Debe ingresar una fecha válida en formato " + dateFormat;
    }
}