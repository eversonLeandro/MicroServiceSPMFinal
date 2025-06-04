package co.edu.unicauca.infra;


import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class renderButton extends JButton implements TableCellRenderer {
    public renderButton(String nombreButton) {
        setText(nombreButton); // Texto del botón
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}

