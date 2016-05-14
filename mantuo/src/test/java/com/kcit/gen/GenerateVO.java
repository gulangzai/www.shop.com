package com.kcit.gen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 生成工作中用到的VO对象 目前只能对单主键的表结构进行处理
 * 
 * @author hp 2010-7-28
 */
public class GenerateVO {

	String packpath = "com.ccthanking.business.pmbzjc.vo";

	String db_user = "pmis";
	String db_pass = "111111";
	String db_url = "jdbc:mysql://192.168.1.117:3306/cpmi_test?useUnicode=true&characterEncoding=utf-8";

	public static void main(String[] args) {

		GenerateVO gv = new GenerateVO();
		gv.setTableName("PM_BZJC");
		gv.setJavaFilePath("D:\\");
		gv.doGenerateVo();
	}

	private String tableName = null;
	private String javaFilePath = null;
	StringBuffer fieldInit = new StringBuffer();
	StringBuffer fieldRQ = new StringBuffer();
	StringBuffer fieldMethod = new StringBuffer();
	StringBuffer fieldPKAndTableName = new StringBuffer();

	public GenerateVO() {
	}

	public GenerateVO(String tableName) {
		setTableName(tableName);
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setJavaFilePath(String path) {
		this.javaFilePath = path;
	}

	public String getJavaFilePath() {
		return this.javaFilePath;
	}

	/**
	 * 根据表名称得到VO名称
	 * 
	 * @return
	 */
	public String getVOName() {
		String[] tempName = tableName.split("_");
		String javaName = "";
		for (String s : tempName) {
			javaName += s.substring(0, 1).toUpperCase()
					+ s.substring(1).toLowerCase();
		}
		javaName = javaName + "VO";
		return javaName;
	}

	/**
	 * 生成VO方法
	 */
	public void doGenerateVo() {
		if (null == tableName) {
			System.out.println("没有设置要生成的表名!");
			return;
		}
		if (null == javaFilePath) {
			System.out.println("没有设置要生成java文件的路径!");
			return;
		}

		System.out.println("生成VO文件开始!");
		doGenerateVO();

		try {
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(getJavaFilePath() + getVOName()
							+ ".java"), "UTF-8");

			// DataOutputStream out = new DataOutputStream(new
			// BufferedOutputStream(new FileOutputStream(getJavaFilePath() +
			// getVOName()
			// + ".java")));

			StringBuffer temp = new StringBuffer();
			temp.append("package " + packpath + ";");
			temp.append("\n");
			temp.append("import com.ccthanking.framework.base.BaseVO;");
			temp.append("\n");
			temp.append("import java.util.Date;");
			temp.append("\n\n");
			temp.append("public class " + getVOName() + " extends BaseVO{");
			temp.append("\n\n");
			appendTab(temp, 1);
			temp.append("public " + getVOName() + "(){");
			temp.append("\n");

			// out1.write(str);

			out.write(temp.toString());
			out.write(fieldInit.toString());
			out.write(fieldRQ.toString());
			out.write(fieldPKAndTableName.toString());
			temp = new StringBuffer();
			appendTab(temp, 1);
			temp.append("}");
			temp.append("\n\n");
			out.write(temp.toString());
			out.write(fieldMethod.toString());
			temp = new StringBuffer();
			temp.append("}");
			out.write(temp.toString());
			out.close();
			System.out.println("生成VO文件结束!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doGenerateVO() {

		if (null == tableName) {
			System.out.println("没有设置要生成的表名!");
			return;
		}

		String sql = "select * from " + tableName + " where 1=2";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		DatabaseMetaData dmd = null;
		String pkColumn = null;
		boolean isGotPk = false;

		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			dmd = conn.getMetaData();

			// 表主键
			rs = dmd.getPrimaryKeys(null, db_user, tableName);
			while (rs.next()) {
				pkColumn = rs.getString(4).toUpperCase();
			}
			rs.close();

			// 表的列注释信息
			Map<String, String> remarkMap = new HashMap<String, String>();
			rs = dmd.getColumns(null, "%", tableName, "%");
			while (rs.next()) {
				remarkMap.put(rs.getString("COLUMN_NAME"), rs
						.getString("REMARKS"));
			}

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String columnName = rsmd.getColumnName(i).toUpperCase();
				int columnType = rsmd.getColumnType(i);
				if (!isGotPk && null != pkColumn) {
					if (columnName.equals(pkColumn)) {
						appendTab(fieldInit, 2);
						fieldInit
								.append("this.addField(\"" + columnName + "\","
										+ getFiledType(columnType)
										+ "|this.TP_PK);//"
										+ remarkMap.get(columnName));
						fieldInit.append("\n");
						isGotPk = true;
					} else {
						appendTab(fieldInit, 2);
						fieldInit.append("this.addField(\"" + columnName
								+ "\"," + getFiledType(columnType) + ");//"
								+ remarkMap.get(columnName));
						fieldInit.append("\n");
					}
				} else {
					appendTab(fieldInit, 2);
					fieldInit.append("this.addField(\"" + columnName + "\","
							+ getFiledType(columnType) + ");//"
							+ remarkMap.get(columnName));
					fieldInit.append("\n");
				}
				doGenerateRQ(fieldRQ, columnName, columnType);
				appendTab(fieldMethod, 1);
				fieldMethod.append("public void set"
						+ columnName.substring(0, 1)
						+ columnName.substring(1).toLowerCase() + "("
						+ getFiledParaType(columnType) + " "
						+ columnName.toLowerCase() + "){");
				fieldMethod.append("\n\t");
				appendTab(fieldMethod, 1);
				fieldMethod.append("this.setInternal(\"" + columnName + "\","
						+ columnName.toLowerCase() + ");");
				fieldMethod.append("\n");
				appendTab(fieldMethod, 1);
				fieldMethod.append("}");
				fieldMethod.append("\n");
				appendTab(fieldMethod, 1);
				fieldMethod.append("public " + getFiledParaType(columnType)
						+ " get" + columnName.substring(0, 1)
						+ columnName.substring(1).toLowerCase() + "(){");
				fieldMethod.append("\n\t");
				appendTab(fieldMethod, 1);
				fieldMethod.append("return (" + getFiledParaType(columnType)
						+ ")this.getInternal(\"" + columnName + "\");");
				fieldMethod.append("\n");
				appendTab(fieldMethod, 1);
				fieldMethod.append("}");
				fieldMethod.append("\n");
			}
			appendTab(fieldPKAndTableName, 2);
			fieldPKAndTableName.append("this.setVOTableName(\"" + tableName
					+ "\");");
			fieldPKAndTableName.append("\n");
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获得字段set和get的参数类型
	 * 
	 * @param type
	 * @return
	 */
	private String getFiledParaType(int type) {
		String result = "String";
		switch (type) {
		case java.sql.Types.TIMESTAMP:
			result = "Date";
			break;
		case java.sql.Types.DATE:
			result = "Date";
			break;
		case java.sql.Types.BLOB:
			result = "byte[]";
			break;
		case java.sql.Types.VARCHAR:
			result = "String";
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 获得字段类型
	 * 
	 * @param type
	 * @return
	 */
	private String getFiledType(int type) {
		String result = "OP_STRING";
		switch (type) {
		case java.sql.Types.TIMESTAMP:
			result = "OP_DATE";
			break;
		case java.sql.Types.DATE:
			result = "OP_DATE";
			break;
		case java.sql.Types.BLOB:
			result = "OP_BLOB";
			break;
		default:
			break;
		}
		return result;
	}

	private void doGenerateRQ(StringBuffer sb, String name, int type) {
		switch (type) {
		case java.sql.Types.TIMESTAMP:
			appendTab(sb, 2);
			sb.append("this.setFieldDateFormat(\"" + name
					+ "\",\"yyyy-MM-dd HH:mm:ss\");");
			sb.append("\n");
			break;
		case java.sql.Types.DATE:
			appendTab(sb, 2);
			sb.append("this.setFieldDateFormat(\"" + name
					+ "\",\"yyyy-MM-dd\");");
			sb.append("\n");
			break;
		default:
			break;
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	private Connection getConnection() {
		Connection conn = null;
		try {

			Properties props = new Properties();
			props.put("user", db_user);
			props.put("password", db_pass);
			props.put("remarksReporting", "true");

			Class.forName("com.mysql.jdbc.Driver");

			// conn =
			// DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL",
			// props);
			conn = DriverManager.getConnection(db_url, props);

			// conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.109:1521:oracle",
			// "wndjs_new", "111111");
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} catch (SQLException e4) {
			e4.printStackTrace();
		} finally {

		}
		return conn;
	}

	/**
	 * 增加tab控制
	 * 
	 * @param sb
	 *            要加入的字符串
	 * @param js
	 *            要加入tab键的个数
	 */
	private void appendTab(StringBuffer sb, int js) {
		for (int i = 0; i < js; i++) {
			sb.append("\t");
		}
	}

}
