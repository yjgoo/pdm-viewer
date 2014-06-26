package org.yjgoo.jpdmviewer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.dom4j.DocumentException;
import org.yjgoo.jpdmviewer.pdm.PDMFileModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.ui.menu.MainMenu;
import org.yjgoo.jpdmviewer.ui.pdm.ModelTree;
import org.yjgoo.jpdmviewer.ui.pdm.ModelTreeNode;
import org.yjgoo.jpdmviewer.ui.pdm.TableInfoPanel;

/**
 * main window
 * 
 * @author yjgoo
 * 
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 5212714499914863032L;
	private ModelTree modelTree;
	private TableInfoPanel tablePanel;

	public MainWindow() {
		this.setBounds(100, 100, 1000, 600);
		this.setTitle("PDM-Viewer V0.1a");
		this.addWindowListener(new WindowListenerHandler());
		this.setMenuBar(new MainMenu(this));
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		this.modelTree = new ModelTree();
		JScrollPane jScrollTree = new JScrollPane(v, h);
		jScrollTree.getViewport().add(modelTree);
		jScrollTree.setPreferredSize(new Dimension(200, 100));
		this.getContentPane().add(jScrollTree, BorderLayout.WEST);

		tablePanel = new TableInfoPanel();
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);

		addEvents();

		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
				new DropFileLoader(this), true, null);
	}

	private void addEvents() {
		modelTree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				ModelTreeNode node = (ModelTreeNode) modelTree
						.getLastSelectedPathComponent();
				TableModel tm;
				if (node != null && node.getData() != null
						&& node.getData() instanceof TableModel) {
					tm = (TableModel) node.getData();
					tablePanel.showModel(tm);
				}
			}
		});
	}

	public void loadPDM(File file) {
		PDMFileModel model;
		try {
			model = PDMFileModel.loadFile(file);
			this.modelTree.refreshModel(model);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private class WindowListenerHandler implements WindowListener {

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}

		public void windowDeactivated(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {

		}

		public void windowOpened(WindowEvent arg0) {
		}

	}

}
