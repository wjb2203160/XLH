package com.mtr.psn.test.generator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.mtr.psn.test.db.DBHelper;

public class ModelGenerator {
	// 表名
	public static String tableName = "data_dictionary";
	// 类名
	public static String modelName = "DataDictionary";
	
	// 包名
	public static String packagePath = "com.mtr.psn";
	// 注意：这里是文件的物理位置，根据自己的项目路径进行修改
	public static String rootPath = "D:\\workspase\\PayStateNuclear\\src\\";
	// 以下不用修改
	public static String mapperName = modelName + "Mapper";
	public static String serviceName = modelName + "Service";
	public static String serviceImplName = modelName + "ServiceImpl";
	// 末尾需要修改到相应 的包内
	public static String mapperPackagePath = packagePath + ".mapper.other";
	public static String modelPackagePath = packagePath + ".model.other";
	public static String servicePackagePath = packagePath + ".service.other";
	public static String serviceImplPackagePath = packagePath + ".service.impl.other";

	public static void main(String[] args) {
		List<DBColumns> columns = getColumns(tableName);
		model(columns);
		mapperXml(columns);
		mapper();
		service();
		serviceImpl();
	}

	/**
	 * 抽取数据库字段
	 * 
	 * @param tableName
	 * @return
	 */
	public static List<DBColumns> getColumns(String tableName) {
		List<DBColumns> columns = new ArrayList<DBColumns>();
		try {
			String sql = "show columns from " + tableName;
			ResultSet rs = DBHelper.executeQuery(sql);
			while (rs.next()) {
				DBColumns column = new DBColumns(rs.getString("Field"), rs.getString("Type"));
				columns.add(column);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return columns;
	}

	/**
	 * 生成model/pojo
	 * 
	 * @param columns
	 */
	public static void model(List<DBColumns> columns) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + modelPackagePath + ";\r\n\r\n");
		sb.append("import java.io.Serializable;\r\n");
		sb.append("import java.util.Date;\r\n");
		sb.append("\r\n");
		sb.append("public class " + modelName + " implements Serializable {\r\n");
		sb.append("\r\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		for (int i = 0, len = columns.size(); i < len; i++) {
			DBColumns column = columns.get(i);
			sb.append("\tprivate " + column.getType() + " " + column.getField() + ";\r\n");
		}
		sb.append("\r\n");
		for (int i = 0, len = columns.size(); i < len; i++) {
			DBColumns column = columns.get(i);
			sb.append(getAndSet(column.getField(), column.getType()));
		}
		sb.append("}");
		try {
			TxtFileUtils.writeTxtFile(sb.toString(), getRealPath(modelPackagePath, modelName + ".java"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 生成mapper
	 */
	public static void mapper() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + mapperPackagePath + ";\r\n");
		sb.append("\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import " + modelPackagePath + "." + modelName + ";\r\n");
		sb.append("\r\n");
		sb.append("public interface " + mapperName + " {\r\n");
		sb.append("\r\n");
		sb.append("\tpublic " + modelName + " selectById(Integer id)throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic List<" + modelName + "> selectAll(" + modelName + " " + toLowerCaseFirstOne(modelName)
				+ ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int insert(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int update(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int delete(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("}\r\n");
		try {
			TxtFileUtils.writeTxtFile(sb.toString(), getRealPath(mapperPackagePath, mapperName + ".java"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 输出MapperXml
	 * 
	 * @param columns
	 */
	public static void mapperXml(List<DBColumns> columns) {
		StringBuffer sb = new StringBuffer();
		StringBuffer fieldStr1 = new StringBuffer();
		StringBuffer fieldStr2 = new StringBuffer();
		StringBuffer fieldStr3 = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		sb.append(
				"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
		sb.append("<mapper namespace=\"" + mapperPackagePath + "." + mapperName + "\">\r\n");
		// selectById
		sb.append("\t<select id=\"selectById\" parameterType=\"Integer\" resultType=\"" + modelName + "\">\r\n");
		sb.append("\t\tSELECT * FROM " + tableName + " WHERE id=#{id} \r\n");
		sb.append("\t</select>\r\n");
		// selectAll
		sb.append(
				"\t<select id=\"selectAll\" parameterType=\"" + modelName + "\" resultType=\"" + modelName + "\">\r\n");
		sb.append("\t\tSELECT * FROM " + tableName + "\r\n");
		sb.append("\t\t<where>\r\n");
		for (int i = 0, len = columns.size(); i < len; i++) {
			String field = columns.get(i).getField();
			sb.append("\t\t\t<if test=\"" + field + " != null\">");
			sb.append("AND " + field + " = #{" + field + "}\r\n");
			sb.append("\t\t\t</if>\r\n");
			// 生成insert 和 update语句参数
			if (i == 0) {
				fieldStr1.append(field);
				fieldStr2.append("#{" + field + "}");
			} else {
				fieldStr1.append("," + field);
				fieldStr2.append(",#{" + field + "}");
				fieldStr3.append("," + field + "=#{" + field + "}");
			}
		}
		sb.append("\t\t</where>\r\n");
		sb.append("\t</select>\r\n");
		// insert
		sb.append("\t<insert id=\"insert\" parameterType=\"" + modelName + "\">\r\n");
		sb.append("\t\tINSERT INTO " + tableName + " (" + fieldStr1.toString() + ")\r\n");
		sb.append("\t\tVALUES (" + fieldStr2.toString() + ")\r\n");
		sb.append("\t</insert>\r\n");
		// update
		sb.append("\t<update id=\"update\" parameterType=\"" + modelName + "\">\r\n");
		sb.append("\t\tUPDATE " + tableName + " SET\r\n");
		sb.append("\t\t" + fieldStr3.toString().substring(1) + "\r\n");
		sb.append("\t\tWHERE id=#{id}\r\n");
		sb.append("\t</update>\r\n");
		// delete
		sb.append("\t<delete id=\"delete\" parameterType=\"" + modelName + "\">\r\n");
		sb.append("\t\tDELETE FROM " + tableName + " WHERE id=#{id}\r\n");
		sb.append("\t</delete>\r\n");
		sb.append("</mapper>\r\n");
		try {
			TxtFileUtils.writeTxtFile(sb.toString(), getRealPath(mapperPackagePath, mapperName + ".xml"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	/**
	 * 生成service
	 */
	public static void service() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + servicePackagePath + ";\r\n");
		sb.append("\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import " + modelPackagePath + "." + modelName + ";\r\n");
		sb.append("\r\n");
		sb.append("public interface " + serviceName + " {\r\n");
		sb.append("\r\n");
		sb.append("\tpublic " + modelName + " selectById(Integer id)throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic List<" + modelName + "> selectAll(" + modelName + " " + toLowerCaseFirstOne(modelName)
				+ ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int psn_insert(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int psn_update(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("\tpublic int psn_delete(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception;\r\n");
		sb.append("\r\n");
		sb.append("}\r\n");
		try {
			TxtFileUtils.writeTxtFile(sb.toString(), getRealPath(servicePackagePath, serviceName + ".java"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * 生成serviceImpl
	 */
	public static void serviceImpl() {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + servicePackagePath + ";\r\n");
		sb.append("\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("\r\n");
		sb.append("import javax.annotation.Resource;\r\n");
		sb.append("import org.springframework.stereotype.Service;\r\n");
		sb.append("import " + mapperPackagePath + "." + mapperName + ";\r\n");
		sb.append("import " + modelPackagePath + "." + modelName + ";\r\n");
		sb.append("\r\n");
		sb.append("@Service\r\n");
		sb.append("public class " + serviceImplName + " implements " + serviceName + " {\r\n");
		sb.append("\r\n");
		sb.append("\t@Resource\r\n");
		sb.append("\tprivate " + mapperName + " " + toLowerCaseFirstOne(mapperName) + ";\r\n");
		sb.append("\r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic " + modelName + " selectById(Integer id)throws Exception{\n");
		sb.append("\t\treturn\t" + toLowerCaseFirstOne(mapperName) + "." + "selectById(id);\r\n");
		sb.append("\t}\r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic List<" + modelName + "> selectAll(" + modelName + " " + toLowerCaseFirstOne(modelName)
				+ ")throws Exception{\n");
		sb.append("\t\treturn\t" + toLowerCaseFirstOne(mapperName) + "." + "selectAll(" + toLowerCaseFirstOne(modelName)
				+ ");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic int psn_insert(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception{\n");
		sb.append("\t\treturn\t" + toLowerCaseFirstOne(mapperName) + "." + "insert(" + toLowerCaseFirstOne(modelName)
				+ ");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic int psn_update(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception{\n");
		sb.append("\t\treturn\t" + toLowerCaseFirstOne(mapperName) + "." + "update(" + toLowerCaseFirstOne(modelName)
				+ ");\r\n");
		sb.append("\t}\r\n");
		sb.append("\t@Override\r\n");
		sb.append("\tpublic int psn_delete(" + modelName + " " + toLowerCaseFirstOne(modelName) + ")throws Exception{\n");
		sb.append("\t\treturn\t" + toLowerCaseFirstOne(mapperName) + "." + "delete(" + toLowerCaseFirstOne(modelName)
				+ ");\r\n");
		sb.append("\t}\r\n");
		sb.append("}\r\n");
		try {
			TxtFileUtils.writeTxtFile(sb.toString(), getRealPath(servicePackagePath, serviceImplName + ".java"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * get和set方法
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getAndSet(String name, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic ").append(type).append(" get").append(toUpperCaseFirstOne(name)).append("() {\r\n");
		sb.append("\t\treturn ").append(name).append(";\r\n");
		sb.append("\t}\r\n");
		sb.append("\r\n");
		sb.append("\tpublic void").append(" set").append(toUpperCaseFirstOne(name)).append("(").append(type).append(" ")
				.append(name).append(") {\r\n");
		sb.append("\t\tthis.").append(name).append(" = ").append(name).append(";\r\n");
		sb.append("\t}\r\n");
		sb.append("\r\n");
		return sb.toString();
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/*
	 * 获取真实路径
	 */
	public static String getRealPath(String pkg, String fileName) {
		return rootPath + StringUtils.replace(pkg, ".", "\\") + "\\" + fileName;
	}
}
