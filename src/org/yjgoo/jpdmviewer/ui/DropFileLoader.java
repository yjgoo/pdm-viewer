package org.yjgoo.jpdmviewer.ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * 文件拖入加载
 * 
 * @author yjgoo
 * 
 */
public class DropFileLoader implements DropTargetListener {
	private MainWindow window;

	public DropFileLoader(MainWindow window) {
		super();
		this.window = window;
	}

	public void dragEnter(DropTargetDragEvent arg0) {
	}

	public void dragExit(DropTargetEvent arg0) {
	}

	public void dragOver(DropTargetDragEvent arg0) {
	}

	public void drop(DropTargetDropEvent dtde) {
		if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
			// Accept the drop and get the transfer data
			dtde.acceptDrop(dtde.getDropAction());
			Transferable transferable = dtde.getTransferable();
			boolean dropSucceeded = false;

			try {
				window
						.setCursor(Cursor
								.getPredefinedCursor(Cursor.WAIT_CURSOR));

				dropSucceeded = dropFile(dtde.getDropAction(), transferable,
						dtde.getLocation());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				window.setCursor(Cursor.getDefaultCursor());
				dtde.dropComplete(dropSucceeded);
			}
		} else {
			dtde.dropComplete(false);
		}
	}

	public void dropActionChanged(DropTargetDragEvent arg0) {
	}

	protected boolean dropFile(int action, Transferable transferable,
			Point location) throws IOException, UnsupportedFlavorException,
			MalformedURLException {
		List files = (List) transferable
				.getTransferData(DataFlavor.javaFileListFlavor);

		if (files != null && files.size() > 0 && files.get(0) instanceof File) {
			window.loadPDM((File) files.get(0));
		}
		return true;
	}
}
