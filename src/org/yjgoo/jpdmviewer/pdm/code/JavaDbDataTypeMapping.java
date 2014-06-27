package org.yjgoo.jpdmviewer.pdm.code;

import java.util.HashMap;
import java.util.Map;

public class JavaDbDataTypeMapping {

	/**
	 * db : java
	 */
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("bit", "Integer");
		map.put("tinyint", "Long");
		map.put("smallint", "Long");
		map.put("int", "Long");
		map.put("bigint", "Long");
		map.put("float", "Double");
		map.put("double", "Double");
		map.put("decimal", "Double");
		map.put("number", "Double");

		map.put("varchar", "String");
		map.put("char", "String");
		map.put("tinytext", "String");
		map.put("mediumtext", "String");
		map.put("text", "String");
		map.put("longtext", "String");
		map.put("varchar2", "String");
		map.put("nvarchar2", "String");
		map.put("nchar", "String");

		map.put("tinyblob", "byte[]");
		map.put("mediumblob", "byte[]");
		map.put("blob", "byte[]");
		map.put("longblob", "byte[]");
		map.put("date", "Date");
		map.put("time", "Date");
		map.put("datetime", "Date");
	}

	public String toJava(String type) {
		if (type == null) {
			return type;
		}
		if (type.indexOf('(') > -1) {
			type = type.substring(0, type.indexOf('('));
		}
		type = type.trim().toLowerCase();
		if (map.containsKey(type)) {
			return map.get(type);
		}
		return type;
	}

}
