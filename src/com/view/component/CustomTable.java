package view.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CustomTable extends JTable {
    private final Color TABLE_PANE_COLOR = new Color(118, 138, 150);
    private final Font TABLE_FONT = new Font("Montserrat", Font.PLAIN, 18);
    private final Font HEADER_FONT = new Font("Montserrat", Font.BOLD, 18);
    private final Color HEADER_BACKGROUND_COLOR = new Color(210, 237, 232);
    private boolean editable = true;

    public CustomTable(DefaultTableModel tableModel) {
        this(tableModel, true);
    }

    public CustomTable(DefaultTableModel tableModel, boolean editable) {
        super(tableModel);
        this.editable = editable;
        setRowHeight(30);
        setFont(TABLE_FONT);

        //Set header
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(HEADER_FONT);
        tableHeader.setBackground(HEADER_BACKGROUND_COLOR);

        setCellSelectionEnabled(false); // disable selection

        //Set text to center
        setCenter();

    }

    public void setCenter() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }
    }

    public JScrollPane createTablePane(int x, int y, int width, int height) {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(TABLE_PANE_COLOR);
        return scrollPane;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }




}