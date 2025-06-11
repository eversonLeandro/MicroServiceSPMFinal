package co.edu.unicauca.microserviceproject.domain.model.project.VO;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ValueObjectsTest {

    @Test
    void testPresupuestoValid() {
        Presupuesto presupuesto = Presupuesto.of(new BigDecimal("5000000"));
        assertNotNull(presupuesto);
        assertEquals(new BigDecimal("5000000"), presupuesto.getValor());
    }

    @Test
    void testPresupuestoInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Presupuesto.of(new BigDecimal("-1000"));
        });
    }

    @Test
    void testFechaEntregaValid() {
        FechaEntrega fecha = FechaEntrega.of(LocalDate.now().plusDays(1));
        assertNotNull(fecha);
    }

    @Test
    void testFechaEntregaInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            FechaEntrega.of(LocalDate.now().minusDays(1));
        });
    }

    @Test
    void testPeriodoAcademicoValid() {
        PeriodoAcademico periodo = PeriodoAcademico.of("2025-1");
        assertNotNull(periodo);
        assertEquals("2025-1", periodo.getPeriodo());
    }

    @Test
    void testPeriodoAcademicoInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            PeriodoAcademico.of("20251");
        });
    }
}