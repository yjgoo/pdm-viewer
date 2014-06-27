package org.yjgoo.jpdmviewer.ui.pdm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.pdm.code.JavaBeanGenerator;
import org.yjgoo.jpdmviewer.ui.DataCleanable;

/**
 * convert table to java bean code or other development language entity code
 * 
 * @author LiuChuanfeng
 * 
 */
public class BeanConvertorPanel extends JPanel implements DataCleanable {
	private static final long serialVersionUID = -1172152208152896866L;
	private JButton createBtn;
	private JTextArea codeArea;
	private JavaBeanGenerator beanGenerator;
	private TableModel tm;

	public BeanConvertorPanel() {
		super(new BorderLayout());

		createBtn = new JButton();
		createBtn.setText("Generator");
		codeArea = new JTextArea();

		this.add(createBtn, BorderLayout.NORTH);
		this.add(codeArea, BorderLayout.CENTER);

		beanGenerator = new JavaBeanGenerator();

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tm != null) {
					codeArea.setText(beanGenerator.convert(tm));
				}
			}
		});
	}

	public void setTableModel(TableModel tm) {
		this.tm = tm;
	}

	public void clean() {
		codeArea.setText("");
	}

}
