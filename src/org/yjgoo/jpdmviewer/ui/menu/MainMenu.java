package org.yjgoo.jpdmviewer.ui.menu;

import java.awt.FileDialog;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import org.yjgoo.jpdmviewer.ui.MainWindow;

public class MainMenu extends MenuBar {
	private static final long serialVersionUID = 1121840949615145249L;
	private MainWindow o;

	public MainMenu(MainWindow o) {
		initFileMenu();
		this.o = o;
	}

	private void initFileMenu() {
		this.add(new Menu("文件"));

		this.getMenu(0).add(new MenuItem("打开"));
		this.getMenu(0).getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileHandler();
			}
		});
	}

	private void openFileHandler() {
		// JFileChooser chooser = new JFileChooser();
		// MyFileFilter filter = new MyFileFilter();
		// chooser.setFileFilter(filter);
		// int returnVal = chooser.showOpenDialog(o);
		// if (returnVal == JFileChooser.APPROVE_OPTION) {
		// o.loadPDM(chooser.getSelectedFile());
		// }
		FileDialog fileselect = new FileDialog(o, "选择一个PDM文件");
		// fileselect.setFilterNames(new String[]{"图片文件"});
		// fileselect.setFilterExtensions(new String[]{"*.jpg"});
		fileselect.setVisible(true);
		fileselect.setFilenameFilter(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (dir.isDirectory())
					return true;
				if (name != null && name.toLowerCase().endsWith(".pdm")) {
					return true;
				}
				return false;
			}
		});
		String url = fileselect.getDirectory() + fileselect.getFile();
		if (url != null) {
			o.loadPDM(new File(url));
		}
	}

}
