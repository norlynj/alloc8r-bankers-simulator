package view.component;

import javax.swing.table.DefaultTableModel;

public class ProcessTableModel extends DefaultTableModel {

    public ProcessTableModel(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
    @Override
    public Object getValueAt(int row, int col) {
        // Set value of column 0 to row number
        if (col == 0) {
            return "P" + (row + 1);
        }
        return super.getValueAt(row, col);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 0;
    }


}