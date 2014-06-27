package org.yjgoo.jpdmviewer.pdm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author yjgoo
 *
 */
public class PDMFileModel {
	/**
	 * Table Model List
	 */
	private ArrayList<TableModel> tml = new ArrayList<TableModel>();

	public static PDMFileModel loadFile(File file) throws DocumentException {
		SAXReader sr = new SAXReader();
		return parse(sr.read(file));
	}

	public static PDMFileModel parse(Document doc) {
		PDMFileModel instance = new PDMFileModel();
		Element root = doc.getRootElement();
		List<Element> els = root.elements();
		els = els.get(0).elements();
		els = els.get(0).elements();
		els = els.get(0).element("Tables").elements();

		// parse tables
		TableModel tm;
		ColumnModel col;
		List<Element> cols;
		Element eCols;
		for (Element t : els) {
			tm = new TableModel();
			tm.setId(t.attributeValue("Id"));
			tm.setObjectId(t.elementText("ObjectId"));
			tm.setName(t.elementText("Name"));
			tm.setCode(t.elementText("Code"));
			tm.setCreationDate(t.elementText("CreationDate"));
			tm.setCreator(t.elementText("Creator"));
			tm.setModificationDate(t.elementText("ModificationDate"));
			tm.setModifier(t.elementText("Modifier"));
			// System.out.println(t.asXML());
			eCols = t.element("Columns");
			if (eCols != null) {
				cols = eCols.elements("Column");
				for (Element c : cols) {
					col = new ColumnModel();
					col.setId(c.attributeValue("Id"));
					col.setObjectId(c.elementText("ObjectId"));
					col.setName(c.elementText("Name"));
					col.setCode(c.elementText("Code"));
					col.setCreationDate(c.elementText("CreationDate"));
					col.setCreator(c.elementText("Creator"));
					col.setModificationDate(c.elementText("ModificationDate"));
					col.setModifier(c.elementText("Modifier"));
					col.setDataType(c.elementText("DataType"));
					col.setLength(c.elementText("Length"));
					col.setComment(c.elementText("Comment"));
					tm.addColumn(col);
				}
			}

			instance.tml.add(tm);
		}
		return instance;
	}

	public ArrayList<TableModel> getTableModelList() {
		return tml;
	}

	public void setTableModelList(ArrayList<TableModel> tml) {
		this.tml = tml;
	}

}
