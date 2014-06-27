package org.yjgoo.jpdmviewer.pdm.code;

import org.yjgoo.jpdmviewer.pdm.ColumnModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;

public class JavaBeanGenerator {
	private JavaDbDataTypeMapping mapping = new JavaDbDataTypeMapping();

	public String convert(TableModel tm) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class ").append(toClassName(tm.getCode())).append(
				" {\n");
		for (ColumnModel cm : tm.getCols()) {
			sb.append("   private ").append(mapping.toJava(cm.getDataType()))
					.append(" ").append(convertStyle(cm.getCode(), false));
			sb.append(";\n");
		}
		sb.append("}");
		return sb.toString();
	}

	private String toClassName(String name) {
		if (name == null || name.length() == 0) {
			return "";
		}
		return convertStyle(name, true);
	}

	private String convertStyle(String code, boolean firstCharUpper) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == '_') {
				if (i + 1 < code.length()) {
					sb.append(Character.toUpperCase(code.charAt(i + 1)));
					i++;
				}
			} else {
				if (firstCharUpper && i == 0) {
					sb.append(Character.toUpperCase(code.charAt(i)));
				} else {
					sb.append(Character.toLowerCase(code.charAt(i)));
				}
			}
		}
		return sb.toString();
	}
}
