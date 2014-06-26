package org.yjgoo.jpdmviewer.ui.pdm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.pdm.code.CreateTableDDLGenerator;

public class TableInfoPanel extends JTabbedPane {
	private static final long serialVersionUID = 4951114711942060043L;
	private ModelTable modelTable;
	private JTextArea sqlTextArea;
	private JTextField tableNameTf;
	private JTextField tableCodeTf;

	public TableInfoPanel() {
		this.setBackground(new Color(0x000000));
		this.modelTable = new ModelTable();
		sqlTextArea = new JTextArea();
		tableNameTf = new JTextField();
		tableCodeTf = new JTextField();

		JPanel tableBase = new JPanel(new BorderLayout());
		tableBase.add(new JScrollPane(modelTable));

		JPanel tableBaseFields = new JPanel();
		tableBaseFields.setLayout(new BoxLayout(tableBaseFields,
				BoxLayout.Y_AXIS));
		tableBaseFields.add(tableNameTf);
		tableBaseFields.add(tableCodeTf);
		tableBase.add(tableBaseFields, BorderLayout.NORTH);

		this.addTab("基本信息", tableBase);

		this.addTab("SQL", sqlTextArea);

	}

	public void showModel(TableModel tm) {
		tableNameTf.setText(tm.getName());
		tableCodeTf.setText(tm.getCode());
		CreateTableDDLGenerator g=new CreateTableDDLGenerator();
		this.sqlTextArea.setText(g.convert(tm));
		modelTable.showModel(tm);
	}

}
