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
		tcm.addColumn(newCol("编号", 50));
		tcm.addColumn(newCol("名称", 200));
		tcm.addColumn(newCol("字段名", 200));
		tcm.addColumn(newCol("数据类型", 100));

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

			vs.add(data);
		}

		Vector<String> vCols = new Vector<String>();
		vCols.add("编号");
		vCols.add("名称");
		vCols.add("字段名");
		vCols.add("数据类型");
		DefaultTableModel dtm = new DefaultTableModel(vs, vCols);

		this.setModel(dtm);
		updateUI();
	}

}
