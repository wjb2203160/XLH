package com.mtr.psn.test.generator;

public class DBColumns {
	private String field; // 字段名
	private String type; // 字段类型

	public DBColumns() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DBColumns(String field, String type) {
		super();
		this.field = toLowerCaseFirstOne(field);
		this.type = parseType(type);
	}

	/**
	 * 将数据库类型转为JAVA类型
	 * 
	 * @param type
	 * @return
	 */
	public String parseType(String type) {
		String typeStr = "";
		int index = type.indexOf("(");
		if (index > -1) {
			typeStr = type.substring(0, index);
		} else {
			typeStr = type;
		}
		if (typeStr.equalsIgnoreCase("char")
				|| typeStr.equalsIgnoreCase("varchar")
				|| typeStr.equalsIgnoreCase("text")) {
			return "String";
		} else if (typeStr.equalsIgnoreCase("smallint")
				|| typeStr.equalsIgnoreCase("int")
				|| typeStr.equalsIgnoreCase("bigint")) {
			return "Integer";
		} else if (typeStr.equalsIgnoreCase("date")
				|| typeStr.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (typeStr.equalsIgnoreCase("float")) {
			return "float";
		} else {
			return "Object";
		}
	}

	/**
	 * 首字母小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
