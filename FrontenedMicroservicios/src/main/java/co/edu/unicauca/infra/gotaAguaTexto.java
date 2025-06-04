
package co.edu.unicauca.infra;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author evers
 */
public class gotaAguaTexto extends JTextField {
    private String watermark;

    public gotaAguaTexto(String watermark) {
        this.watermark = watermark;
        setText(watermark);
        setForeground(Color.GRAY); // Color gris para el texto guía

        // Agregar un listener para manejar el foco del campo
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(watermark)) {
                    setText("");
                    setForeground(Color.BLACK); // Cambia a negro cuando el usuario escribe
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(watermark);
                    setForeground(Color.GRAY); // Regresa el color gris si está vacío
                }
            }
        });
    }
}
