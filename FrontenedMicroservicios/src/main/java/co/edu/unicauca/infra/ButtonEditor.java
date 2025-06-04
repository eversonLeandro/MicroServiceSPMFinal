package co.edu.unicauca.infra;

import javax.swing.table.TableCellEditor;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;

public class ButtonEditor<T extends IButtonContext> extends AbstractCellEditor implements TableCellEditor {

    private JButton button;
    private JTable table;
    private int row;
    private IButtonAction<T> action;
    private T context;

    public ButtonEditor(JTable table, T context, IButtonAction<T> action, String buttonText) {
        this.table = table;
        this.context = context;
        this.action = action;
        button = new JButton(buttonText);

        button.addActionListener(e -> {
            if (this.context instanceof verProyectPostuladoContext) {
                ((verProyectPostuladoContext) context).setRow(row);
            } 
            else if (this.context instanceof PostularseContext) {
                ((PostularseContext) context).setRow(row);
            }

            action.execute(context);
            fireEditingStopped();
        });

    }

    // Inside your ButtonEditor class
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row; // Guardar la fila actual
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }
}