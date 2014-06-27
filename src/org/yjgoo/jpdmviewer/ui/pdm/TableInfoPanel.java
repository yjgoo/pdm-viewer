package org.yjgoo.jpdmviewer.ui.pdm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.yjgoo.jpdmviewer.pdm.ColumnModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.pdm.code.CreateTableDDLGenerator;

public class TableInfoPanel extends JTabbedPane {
	private static final long serialVersionUID = 4951114711942060043L;
	private ModelTable modelTable;
	private JTextArea sqlTextArea;
	private JTextField tableNameTf;
	private JTextField tableCodeTf;
	private JTextArea commentTa;
	TableModel tm;

	public TableInfoPanel() {
		this.setBackground(new Color(0x000000));
		this.modelTable = new ModelTable();
		sqlTextArea = new JTextArea();
		tableNameTf = new JTextField();
		tableCodeTf = new JTextField();
		commentTa = new JTextArea();

		JPanel tableBase = new JPanel(new BorderLayout());
		tableBase.add(new JScrollPane(modelTable));

		JPanel tableBaseFields = new JPanel();
		tableBaseFields.setLayout(new BoxLayout(tableBaseFields,
				BoxLayout.Y_AXIS));
		tableBaseFields.add(tableNameTf);
		tableBaseFields.add(tableCodeTf);
		tableBase.add(tableBaseFields, BorderLayout.NORTH);

		JPanel commentPanel = new JPanel();
		commentTa.setPreferredSize(new Dimension(100, 100));
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		commentPanel.add(commentTa);
		tableBase.add(commentPanel, BorderLayout.SOUTH);

		this.addTab("Table", tableBase);
		this.addTab("SQL", sqlTextArea);
		
		initEvents();
	}

	private void initEvents() {
		// item selection
		this.modelTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						// show comment
						DefaultListSelectionModel lse = (DefaultListSelectionModel) e
								.getSource();
						if (lse.getMinSelectionIndex() > -1
								&& lse.getMinSelectionIndex() <= tm.getCols()
										.size()) {
							ColumnModel colMod = tm.getCols().get(
									lse.getMinSelectionIndex());
							commentTa.setText(colMod.getComment());
							commentTa.updateUI();
						}
					}
				});
	}

	public void showModel(TableModel tm) {
		this.tm = tm;
		tableNameTf.setText(tm.getName());
		tableCodeTf.setText(tm.getCode());
		CreateTableDDLGenerator g = new CreateTableDDLGenerator();
		this.sqlTextArea.setText(g.convert(tm));
		modelTable.showModel(tm);
	}

}
