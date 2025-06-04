package co.edu.unicauca.microserviceproject.domain.model.project.state;

public class EstadoFactory {

    public static ProjectState crearEstado(String tipoEstado) {
        if (tipoEstado == null) {
            throw new IllegalArgumentException("El tipo de estado no puede ser nulo");
        }

        return switch (tipoEstado.toUpperCase()) {
            case "RECIBIDO" -> new RecibidoState();
            case "ACEPTADO" -> new AceptadoState();
            case "RECHAZADO" -> new RechazadoState();
            case "EN EJECUCION" -> new EnEjecucionState();
            case "CERRADO" -> new CerradoState();
            default -> throw new IllegalArgumentException("Estado no válido: " + tipoEstado);
        };
    }
}
