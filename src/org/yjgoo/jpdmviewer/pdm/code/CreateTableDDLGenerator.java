package org.yjgoo.jpdmviewer.pdm.code;

import org.yjgoo.jpdmviewer.pdm.ColumnModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;

/**
 * 
 * @author yjgoo
 *
 */
public class CreateTableDDLGenerator {

	public String convert(TableModel tm) {
		StringBuffer sb = new StringBuffer();
		sb.append("create table ").append(tm.getCode()).append(" (\n");
		int i=tm.getCols().size();
		for (ColumnModel cm : tm.getCols()) {
			sb.append("   ").append(cm.getCode()).append(" ").append(cm.getDataType());
			if(i-->1){
				sb.append(",");
			}
			sb.append("\n");
		}
		sb.append(")");
		return sb.toString();
	}

}
