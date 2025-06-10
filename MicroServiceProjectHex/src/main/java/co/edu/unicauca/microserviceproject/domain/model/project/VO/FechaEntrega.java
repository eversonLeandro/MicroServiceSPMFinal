package co.edu.unicauca.microserviceproject.domain.model.project.VO;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FechaEntrega {

    private final LocalDate fecha;

    private FechaEntrega(LocalDate fecha) {
        this.fecha = fecha;
    }

    public static FechaEntrega of(LocalDate fecha) {
        Objects.requireNonNull(fecha, "La fecha de entrega no puede ser nula");
        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser anterior a hoy");
        }
        return new FechaEntrega(fecha);
    }


    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FechaEntrega)) return false;
        FechaEntrega that = (FechaEntrega) o;
        return fecha.equals(that.fecha);
    }

    @Override
    public int hashCode() {
        return fecha.hashCode();
    }

    @Override
    public String toString() {
        return "FechaEntrega{" + "fecha=" + fecha + '}';
    }
}