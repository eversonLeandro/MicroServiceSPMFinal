package co.edu.unicauca.microserviceproject.domain.model.project.VO;

import java.util.Objects;
import java.util.regex.Pattern;

public class PeriodoAcademico {

    private static final Pattern FORMATO_VALIDO = Pattern.compile("^\\d{4}-(1|2)$");

    private final String periodo;

    private PeriodoAcademico(String periodo) {
        this.periodo = periodo;
    }

    public static PeriodoAcademico of(String periodo) {
        Objects.requireNonNull(periodo, "El periodo académico no puede ser nulo");
        if (!FORMATO_VALIDO.matcher(periodo).matches()) {
            throw new IllegalArgumentException("El periodo académico debe tener formato yyyy-1 o yyyy-2, ejemplo: 2025-1");
        }
        return new PeriodoAcademico(periodo);
    }

    public String getPeriodo() {
        return periodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodoAcademico)) return false;
        PeriodoAcademico that = (PeriodoAcademico) o;
        return periodo.equals(that.periodo);
    }

    @Override
    public int hashCode() {
        return periodo.hashCode();
    }

    @Override
    public String toString() {
        return "PeriodoAcademico{" +
                "periodo='" + periodo + '\'' +
                '}';
    }
}