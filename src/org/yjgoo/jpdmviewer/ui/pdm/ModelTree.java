package org.yjgoo.jpdmviewer.ui.pdm;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.yjgoo.jpdmviewer.pdm.PDMFileModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;

public class ModelTree extends JTree {
	private static final long serialVersionUID = 2384351839472362324L;
	private PDMFileModel model;

	public ModelTree() {
		TreeNode root = new ModelTreeNode("PDM");
		DefaultTreeModel tm = new DefaultTreeModel(root);
		this.setModel(tm);
	}

	public void refreshModel(PDMFileModel model) {
		this.model = model;
		ModelTreeNode root = new ModelTreeNode("PDM");
		DefaultTreeModel tm = new DefaultTreeModel(root);
		this.setModel(tm);
		ModelTreeNode tbls = new ModelTreeNode("Tables");
		root.add(tbls);

		// load trees
		ModelTreeNode tbl;
		for (TableModel t : model.getTableModelList()) {
			tbl = new ModelTreeNode(t.getName());
			tbl.setData(t);
			tbls.add(tbl);
		}

		this.expandPath(new TreePath(tbls.getPath()));
	}

}
