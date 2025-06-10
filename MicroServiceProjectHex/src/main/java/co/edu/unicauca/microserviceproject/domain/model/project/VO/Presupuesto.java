package co.edu.unicauca.microserviceproject.domain.model.project.VO;

import java.math.BigDecimal;
import java.util.Objects;

public class Presupuesto {

    private final BigDecimal valor;

    private Presupuesto(BigDecimal valor) {
        this.valor = valor;
    }

    public static Presupuesto of(BigDecimal valor) {
        // Validaciones básicas
        Objects.requireNonNull(valor, "El valor del presupuesto no puede ser nulo");
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor del presupuesto debe ser mayor que cero");
        }

        return new Presupuesto(valor);
    }

    public BigDecimal getValor() {
        return valor;
    }

    // equals y hashCode para comparar correctamente Value Objects

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Presupuesto)) return false;
        Presupuesto that = (Presupuesto) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return valor.hashCode();
    }

    @Override
    public String toString() {
        return "Presupuesto{" +
                "valor=" + valor +
                '}';
    }
}