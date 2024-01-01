package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor {
    private IconButton button;
    
    public ButtonCellEditor(IconButton button, EventListener eventListener) {
    	this.button = button;
    	this.button.setOpaque(true);
    	this.button.addActionListener((ActionListener) eventListener);
    }
	
	public void fireEditingStopped() {
		super.fireEditingStopped();
	}

	@Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	if (row % 2 == 0) {
    		this.button.setBackground(Color.decode("#901A4A"));
    		this.button.setForeground(Color.WHITE);
        } else {
        	this.button.setBackground(Color.WHITE);
        	this.button.setForeground(Color.BLACK);
        }
        return this.button;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}