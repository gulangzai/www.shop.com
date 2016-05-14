package com.ccthanking.framework.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * excel 导入导出工具类
 * 
 * @author Admin
 *
 */
public class ExcelUtils {

	/**
	 * 导出
	 * @param ary
	 * @param dirPath
	 * @param filename
	 */
	public static void excelOut(ArrayList<Object> ary, String dirPath,
			String filename) {
		WritableWorkbook workbook = null;
		File path = new File(dirPath);
		if (!path.exists()) {

			path.mkdir();
		}

		File file = new File(dirPath + "/" + filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			workbook = Workbook.createWorkbook(file);

			WritableSheet sheet = workbook.createSheet("sheet1", 0);

			for (int i = 0; i < ary.size(); i++) {
				Object obj = ary.get(i);
				Class<?> cls = obj.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					field.setAccessible(true);

					if (field.getType().getName().equals("double")) {
						sheet.addCell(new Label(j, i, String.valueOf(field
								.getDouble(obj))));
					} else if (field.getType().getName().equals("int")) {
						sheet.addCell(new Label(j, i, String.valueOf(field
								.getInt(obj))));
					} else {
						sheet.addCell(new Label(j, i, String.valueOf(field
								.get(obj))));
					}
				}

			}
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 *  导入
	 * @param cls        
	 * @param filepath
	 * @return
	 */
	public static ArrayList<Object> excelIn(Class<?> cls, String filepath) {
		ArrayList<Object> ary = new ArrayList<Object>();
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(filepath));
			Sheet sheet = workbook.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {

				Object obj = cls.newInstance();
				Field[] fields = cls.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					Cell cell = sheet.getCell(j, i);
					field.setAccessible(true);
					if (field.getType().getName().equals("int")) {
						field.setInt(obj, Integer
								.parseInt((cell.getContents())));
					} else if (field.getType().getName().equals("double")) {
						field.setDouble(obj, Double.parseDouble(cell
								.getContents()));
					} else {
						field.set(obj, String.valueOf(cell.getContents()));
					}
				}
				ary.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
		}
		return ary;
	}

}
