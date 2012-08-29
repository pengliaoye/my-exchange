package com.exchange.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	/**
	 * poi导出excel
	 * @param data
	 * @param titles
	 * @param fields
	 * @param sheetName
	 * @return
	 */
	public static HSSFWorkbook createExcel(List<?> data,String[] titles,String[] fields,String sheetName){
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet(sheetName);
		HSSFRow row=null;
		HSSFCell cell=null;
		row=sheet.createRow(0);
		for(int i=0;i<titles.length;i++){
			cell=row.createCell(i);
			String title=titles[i];
			cell.setCellValue(title);
		}
		
		for(int i=0;i<data.size();i++){
			row=sheet.createRow(i+1);
			for(int j=0;j<fields.length;j++){
				Object obj=data.get(i);
				String field=fields[j];
				cell=row.createCell(j);
				String value="";
				try {
					value = PropertyUtils.getProperty(obj,field).toString();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cell.setCellValue(value);
			}
		}
		return workBook;
	}
	/**
	 * poi导入excel
	 * @param input
	 * @throws IOException
	 */
	public static void excelImport(InputStream input) throws IOException{
		HSSFWorkbook workBook=new HSSFWorkbook(input);
		int sheetNumber=workBook.getNumberOfSheets();
		for(int i=0;i<sheetNumber;i++){
			HSSFSheet sheet=workBook.getSheetAt(i);
			int rowNum=sheet.getLastRowNum();
			for(int j=0;j<rowNum;j++){
				HSSFRow row=sheet.getRow(j);
				int cellNum=row.getLastCellNum();
				for(int k=0;k<cellNum;k++){
					HSSFCell cell=row.getCell(k);
				}
			}
		}
	}
	/**
	 * jxl导入excel
	 * @param is
	 * @throws BiffException
	 * @throws IOException
	 */
	public static void importExcel(InputStream is) throws BiffException, IOException{
		Workbook workBook=Workbook.getWorkbook(is);
		Sheet[] sheets=workBook.getSheets();
		for(int i=0;i<sheets.length;i++){
			Sheet sheet=sheets[i];
			for(int j=0;j<sheet.getColumns();j++){
				for(int k=0;k<sheet.getRows();k++){
					Cell cell=sheet.getCell(j, k);
					System.out.println(cell.getType());
					System.out.println(cell.getContents());
				}
			}
		}
	}
	/**
	 * jxl导出excel
	 * @param data
	 * @param titles
	 * @param fields
	 * @param sheetName
	 * @param response
	 */
	public static void exportExcel(List<?> data,String[] titles,String[] fields,String sheetName,HttpServletResponse response){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = sdf.format(new Date()) + ".xls";
			response.setContentType("application/x-msexcel;charset=UTF-8");
			response.setHeader("Content-Disposition","attachment; filename="
					+ fileName);
			WritableWorkbook  workBook=Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet=workBook.createSheet(sheetName, 0);
			Label label=null;
			for(int i=0;i<titles.length;i++){
				String title=titles[i];
				label=new Label(i,0,title);
				sheet.addCell(label);
			}
			
			for(int i=0;i<data.size();i++){
				Object obj=data.get(i);
				for(int j=0;j<fields.length;j++){
					String field=fields[j];
					String value = PropertyUtils.getProperty(obj,field).toString();
					label=new Label(j,i+1,value);
					sheet.addCell(label);
				}
			}
			workBook.write();
			workBook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
