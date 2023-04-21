package view.component;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomTableModel extends DefaultTableModel {
    private boolean editable = true;
    private boolean reset = false;
    public CustomTableModel(String[] columnNames, int rowCount, boolean processID) {
        this(columnNames, rowCount);
        this.editable = !processID;
    }

    public CustomTableModel(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public void reset() {
        this.reset = true;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                setValueAt(null, i, j);
            }
        }
        this.reset = false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (!editable && col == 0) {
            return "P" + (row + 1);
        }
        return super.getValueAt(row, col);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (reset || value == null || value.toString().isEmpty()) {
            super.setValueAt(null, row, column);
            return;
        }

        try {
            int intValue = Integer.parseInt(value.toString());

            if (intValue < 0 || intValue > 30) {
                fireTableCellUpdated(row, column);
                return;
            }

            super.setValueAt(intValue, row, column);
            fireTableCellUpdated(row, column);

        } catch (NumberFormatException e) {
        fireTableCellUpdated(row, column);
        }
    }


}