package org.yjgoo.jpdmviewer.ui.pdm;

import javax.swing.tree.DefaultMutableTreeNode;

public class ModelTreeNode extends DefaultMutableTreeNode {
	private static final long serialVersionUID = -2962609346993054856L;
	private Object data;

	public ModelTreeNode(Object userData) {
		super(userData);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
