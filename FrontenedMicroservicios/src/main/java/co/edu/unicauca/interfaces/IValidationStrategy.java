package co.edu.unicauca.interfaces;

public interface IValidationStrategy {
    boolean isValid(String input);
    String getErrorMessage();
}