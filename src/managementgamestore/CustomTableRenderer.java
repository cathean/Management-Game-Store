/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementgamestore;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author ivans
 */
public class CustomTableRenderer {
    // Overriding TableCellRenderer method so we could use image in table cell
    class CellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            TableColumn tb = table.getColumn("Thumbnail");
            tb.setMaxWidth(120);
            tb.setMinWidth(120);
            table.setRowHeight(120);
            
            return (Component) value;
        }
    }
    
    // Overriding TableCellRenderer method so we could use multi-lines 
    class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //make multi line where the cell value is String[]
            if (value instanceof String[]) {
                setListData((String[]) value);
            }

            //cell backgroud color when selected
            if (isSelected) {
                setBackground(UIManager.getColor("Table.selectionBackground"));
            } else {
                setBackground(UIManager.getColor("Table.background"));
            }

            return this;
        }
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
                    setForeground(table.getSelectionForeground());
                    setBackground(table.getSelectionBackground());
                } else {
                    setForeground(table.getForeground());
                    setBackground(UIManager.getColor("Button.background"));
                }
                setText((value == null) ? "" : value.toString());
                return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
          super(checkBox);
          button = new JButton();
          button.setOpaque(true);
          button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              fireEditingStopped();
            }
          });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
          if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
          } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
          }
          label = (value == null) ? "" : value.toString();
          button.setText(label);
          isPushed = true;
          return button;
        }

        public Object getCellEditorValue() {
          if (isPushed) {
            // 
            // 
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
            // System.out.println(label + ": Ouch!");
          }
          isPushed = false;
          return new String(label);
        }

        public boolean stopCellEditing() {
          isPushed = false;
          return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
          super.fireEditingStopped();
        }
    }
}
