package ui;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCellRender extends JLabel implements TableCellRenderer {
	private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		String dateLap;
		LocalDate currentDate = LocalDate.now();
		dateLap = "" + currentDate;
		Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		String ngay1 = table.getModel().getValueAt(row, 3).toString();
		String ngay2 = table.getModel().getValueAt(row, 4).toString();
		int result = 0;
		try {
			Date date1 = sdf.parse(dateLap);
			Date date2 = sdf.parse(ngay2);
			result = date1.compareTo(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result > 0) {
			c.setBackground(Color.YELLOW);
        }else {
            	c.setBackground(Color.WHITE);
            }
       
		if (isSelected) {
			c.setBackground(Color.LIGHT_GRAY);
		}
		return c;
	}

	
}
