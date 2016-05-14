/*
 * Word2PDF.java  v1.00  2014-4-9
 * Peoject	poidemo
 * Copyright (c) 2014 KcpmIT
 *
 * Filename	:	Word2PDF.java  v1.00 2014-4-9
 * Project	: 	poidemo
 * Copyight	:	Copyright (c) 2014 KcpmIT
 */

package com.ccthanking.common.util;
import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * DOC文档转PDF.<br>
 * http://www.iteye.com/topic/1005741
 * 
 * @author <a href="mailto:jianggl88@gmail.com">蒋根亮</a>
 * @version v1.00
 * @since 1.00 2014-4-9
 * 
 */
public class Word2PDF {

	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。

	static final int wdFormatPDF = 17;// PDF 格式

	public static void main(String[] args) {

		String filename = "D:\\MyEclipse8.5\\Workspaces\\wndjs\\workspaces\\poidemo1\\WebRoot\\doc\\十四、排水方案预审_无锡新区城市排水方案预审申请表.xml";

		Word2PDF word2pdf = new Word2PDF();
		word2pdf.toPdf(filename);
	}

	public static void toPdf(String filename) {

		// String filename = "f:/temp/测试文档.docx";
		// String filename =
		// "E:\\tmp\\java\\tools_project\\apache\\poi\\poidemo\\WebRoot\\doc\\04091725277097.doc";
		filename=filename+".xml";
		String toFilename = filename.replace(".xml", ".pdf");
		// System.out.println("启动Word...");
		long start = System.currentTimeMillis();
		ComThread.InitSTA();
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Word.Application");
			app.setProperty("Visible", false);

			Dispatch docs = app.getProperty("Documents").toDispatch();
			// System.out.println("打开文档..." + filename);
			Dispatch doc = Dispatch.call(docs,//
					"Open", //
					filename,// FileName
					Boolean.FALSE,// ConfirmConversions
					Boolean.TRUE // ReadOnly
					).toDispatch();

			// System.out.println("转换文档到PDF..." + toFilename);
			File tofile = new File(toFilename);
			if (tofile.exists()) {
				tofile.delete();
			}
			Dispatch.call(doc,//
					"SaveAs", //
					toFilename, // FileName
					new Integer("17"));

//			Dispatch.call(doc, "Close", Boolean.FALSE);
			Dispatch.call(doc, "Close", new Object[] { new Variant(false) });
			long end = System.currentTimeMillis();
//			System.out.println("转换完成..用时：" + (end - start) + "ms.");
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("========Error:文档转换失败：" + e.getMessage());
		} finally {
			if (app != null){
				app.invoke("Quit", wdDoNotSaveChanges);
				app.safeRelease();
			}
			ComThread.Release();
		}
	}
}
