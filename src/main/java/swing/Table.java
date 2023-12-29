package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.EventListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Table extends JTable {
	EventListener eventListener;
	String[] columnsName;
	ImageIcon imageIcon_edit = new ImageIcon(getClass().getResource("/icons/dot.png"));
	ImageIcon imageIcon_delete = new ImageIcon(getClass().getResource("/icons/dot.png"));
	
	public Table(String[] columnsName, EventListener eventListener) {
		this.eventListener = eventListener;		
		columnsName = Arrays.copyOf(columnsName, columnsName.length + 2);
		columnsName[columnsName.length - 1] = "Delete";
		columnsName[columnsName.length - 2] = "Edit";
		this.columnsName = columnsName;
		setModel(new DefaultTableModel(new Object[][] {}, columnsName) {
			@Override
            public boolean isCellEditable(int row, int column) {
                return column==getColumnCount()-1 || column==getColumnCount()-2;
            }
		});
		customizeTable();
	}
	
	public Table(String[] columnsName, EventListener eventListener, int buttonColumn, JButton button) {
		this.eventListener = eventListener;		
		columnsName = Arrays.copyOf(columnsName, columnsName.length + 2);
		columnsName[columnsName.length - 1] = "Delete";
		columnsName[columnsName.length - 2] = "Edit";
		this.columnsName = columnsName;
		setModel(new DefaultTableModel(new Object[][] {}, columnsName) {
			@Override
            public boolean isCellEditable(int row, int column) {
                return column==getColumnCount()-1 || column==getColumnCount()-2 || column==buttonColumn || column==2;
            }
		});
		getColumnModel().getColumn(buttonColumn).setCellEditor(new ButtonEditor(button));
		customizeTable();
	}
	
	public void addRow(Object[] row) {
		row = Arrays.copyOf(row, row.length + 2);
		row[row.length - 1] = imageIcon_delete;
		row[row.length - 2] = imageIcon_edit;
		((DefaultTableModel) getModel()).addRow(row);
		customizeTable();
	}

	public void customizeTable() {
		JButton button_edit = new JButton();
		button_edit.setIcon(imageIcon_edit);
		button_edit.addMouseListener((MouseListener) eventListener);
		getColumnModel().getColumn(getColumnCount()-2).setCellEditor(new ButtonEditor(button_edit));
		
		JButton button_delete = new JButton();
		button_delete.setIcon(imageIcon_delete);
		button_delete.addMouseListener((MouseListener) eventListener);
        getColumnModel().getColumn(getColumnCount()-1).setCellEditor(new ButtonEditor(button_delete));
        
        setSurrendersFocusOnKeystroke(true);
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		setFont(new Font("Tahoma", Font.PLAIN, 14));
		setShowGrid(false);
		setBackground(Color.WHITE);
		setRowHeight(48);
		
		// set CENTER id, edit, delete head cell
        getColumnModel().getColumn(0).setHeaderRenderer(centerHeaderRenderer);
        getColumnModel().getColumn(getColumnCount()-1).setHeaderRenderer(centerHeaderRenderer);
        getColumnModel().getColumn(getColumnCount()-2).setHeaderRenderer(centerHeaderRenderer);
        
        // set CENTER id data cell
        getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        //set CENTER edit, delete data cell
        getColumnModel().getColumn(getColumnCount()-1).setCellRenderer(centerImageIconRenderer);
        getColumnModel().getColumn(getColumnCount()-2).setCellRenderer(centerImageIconRenderer);
        
        //set LEFT other header cell
        for (int i = 1; i < getColumnModel().getColumnCount()-2; i++) {
            getColumnModel().getColumn(i).setHeaderRenderer(leftHeaderRenderer);
        }
        
        //set LEFT other data cell
        for (int i = 1; i < getColumnModel().getColumnCount()-2; i++) {
            getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
        }
	}
	
	static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        public ButtonEditor(JButton button) {
            super(new JComboBox<>());
            this.button = button;
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        	if (row % 2 == 0) {
        		button.setBackground(Color.decode("#901A4A"));
        		button.setForeground(Color.WHITE);
            } else {
            	button.setBackground(Color.WHITE);
            	button.setForeground(Color.BLACK);
            }
        	button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        	setClickCountToStart(0);
            return button;
        }
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
	
    DefaultTableCellRenderer centerImageIconRenderer = new DefaultTableCellRenderer() {
    	
    	@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            setRowColor(row, component);
            return component;
    	}
    	@Override
	    protected void setValue(Object value) {
	        if (value instanceof ImageIcon) {
	        	System.out.println(((ImageIcon) value).getImage());
	            setIcon((ImageIcon) value);
	            setText("");
	        } else {
	            super.setValue(value);
	        }
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
        @Override
	    protected void setValue(Object value) {
	        if (value instanceof JButton) {
	            setText(((AbstractButton) value).getText());
	        } else {
	            super.setValue(value);
	        }
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
	
	public void setImageCell(ImageIcon imageIcon, int index) {
		getColumnModel().getColumn(index).setHeaderRenderer(centerHeaderRenderer);
		getColumnModel().getColumn(index).setCellRenderer(centerImageIconRenderer);
	}
}
