package org.yjgoo.jpdmviewer.ui.pdm;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.yjgoo.jpdmviewer.pdm.ColumnModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;

public class ModelTable extends JTable {
	private static final long serialVersionUID = 2712141319312755522L;

	private TableModel tm;
	private TableColumnModel tcm;

	public ModelTable() {
		tcm = new DefaultTableColumnModel();
		tcm.addColumn(newCol("No", 50));
		tcm.addColumn(newCol("Name", 200));
		tcm.addColumn(newCol("Field Name", 200));
		tcm.addColumn(newCol("Data Type", 100));
		tcm.addColumn(newCol("comment", 100));

		DefaultTableModel dtm = new DefaultTableModel();
		this.setModel(dtm);
		this.createDefaultColumnsFromModel();
		this.setPreferredScrollableViewportSize(new Dimension(550, 30));
		// setLayout(null);
		setColumnModel(tcm);
		initializeLocalVars();
		updateUI();
	}
	
	private TableColumn newCol(String name, int width) {
		TableColumn tc = new TableColumn();
		tc.setHeaderValue(name);
		tc.setWidth(width);
		return tc;
	}

	public void showModel(TableModel tm) {
		this.tm = tm;
		Vector<Vector> vs = new Vector<Vector>();
		int i = 0;

		for (ColumnModel col : tm.getCols()) {
			Vector<String> data = new Vector<String>();
			data.add("" + ((i++) + 1));
			data.add(col.getName());
			data.add(col.getCode());
			data.add(col.getDataType());
			data.add(col.getComment());
			vs.add(data);
		}

		Vector<String> vCols = new Vector<String>();
		vCols.add("No");
		vCols.add("Name");
		vCols.add("Field Name");
		vCols.add("Data Type");
		vCols.add("Comment");
		DefaultTableModel dtm = new DefaultTableModel(vs, vCols);

		this.setModel(dtm);
		updateUI();
	}

}
