package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
	EventListener eventListener;
	
	public Table(String[] columnNames, EventListener eventListener, Integer[] editableColumns, boolean haveEditAndDeleteColumn) {
		this.eventListener = eventListener;
		
		if(haveEditAndDeleteColumn) {
			columnNames = Arrays.copyOf(columnNames, columnNames.length + 2);
			columnNames[columnNames.length - 1] = "Delete";
			columnNames[columnNames.length - 2] = "Edit";
		}
		
		setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			@Override
            public boolean isCellEditable(int row, int column) {
				if(editableColumns != null) {
					for (Integer editableColumn : editableColumns) {
			            if (editableColumn == column) {
			                return true;
			            }
			        }
				}
				if(haveEditAndDeleteColumn)
					return column==getColumnCount()-1 || column==getColumnCount()-2;
				return false;
            }
		});
		
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setShowGrid(false);
		setBackground(Color.WHITE);
		setRowHeight(48);
		
		for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            setLeftColumn(i);
        }
		
		if(haveEditAndDeleteColumn) {
			JButton button_edit = new JButton();
			button_edit.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
			setJButtonColumn(button_edit, getColumnCount()-2);
			
			JButton button_delete = new JButton();
			button_delete.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
			setJButtonColumn(button_delete, getColumnCount()-1);
		}
	}
	
	public void addRow(Object[] rowData) {
		((DefaultTableModel) getModel()).addRow(rowData);
	}
	
	public void setJButtonColumn(JButton iconButton, int column) {
		getColumnModel().getColumn(column).setCellRenderer(new ButtonCellRenderer(iconButton));
        getColumnModel().getColumn(column).setCellEditor(new ButtonCellEditor(iconButton, eventListener));
        getColumnModel().getColumn(column).setHeaderRenderer(centerHeaderRenderer);
	}
	
	public void setCenterColumn(int column) {
		getColumnModel().getColumn(column).setHeaderRenderer(centerHeaderRenderer);
		getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	
	public void setLeftColumn(int column) {
		getColumnModel().getColumn(column).setHeaderRenderer(leftHeaderRenderer);
        getColumnModel().getColumn(column).setCellRenderer(leftRenderer);
	}
	
	DefaultTableCellRenderer centerHeaderRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(null);
            setHorizontalAlignment(SwingConstants.CENTER);
            setPreferredSize(new Dimension(component.getWidth(), 48));
            setFont(new Font("Tahoma", Font.BOLD, 14));
            setRowColor(row, component);
            return component;
        }
    };
    
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
    	@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            setRowColor(row, component);
            return component;
    	}
    };
    
    DefaultTableCellRenderer leftHeaderRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setPreferredSize(new Dimension(component.getWidth(), 48));
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
            setFont(new Font("Tahoma", Font.BOLD, 14));
            setRowColor(row, component);
            return component;
        }
    };
    
    DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setPreferredSize(new Dimension(component.getWidth(), 48));
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
            setRowColor(row, component);
            return component;
        }
    };
    
    private void setRowColor(int row, Component component) {
    	if (row % 2 == 0) {
        	component.setBackground(Color.decode("#901A4A"));
        	component.setForeground(Color.WHITE);
        } else {
        	component.setBackground(Color.WHITE);
        	component.setForeground(Color.BLACK);
        }
    }
}

class ButtonCellRenderer extends DefaultTableCellRenderer {
    private JButton button;
    
    public ButtonCellRenderer(JButton button) {
    	this.button = new JButton();
    	this.button.setOpaque(true);
    	this.button.setBorderPainted(false);
    	this.button.setIcon(button.getIcon());
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	if (row % 2 == 0) {
    		this.button.setBackground(Color.decode("#901A4A"));
    		this.button.setForeground(Color.WHITE);
        } else {
        	this.button.setBackground(Color.WHITE);
        	this.button.setForeground(Color.BLACK);
        }
    	return this.button;
    }
}