package com.parabank.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelData {

	 public static XSSFWorkbook workbook;
	 public static XSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 //public static String file_location = "E:\\Amazon\\Eshikshakosh\\data\\CustomerDetails.xlsx";
	 public static String file_location = System.getProperty("user.dir")+"\\TestData\\CustomerDetails.xlsx";
	 static String sheetName= "Customer";
	//
	public static Object[][] readExcel() throws IOException, InvalidFormatException
	{
		//FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
		workbook = new XSSFWorkbook (new File(file_location)); //get my workbook 
		worksheet=workbook.getSheet(sheetName);// get my sheet from workbook
		XSSFRow Row=worksheet.getRow(0);   //get my Row which start from 0   
		   
		     int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		     int ColNum= Row.getLastCellNum(); // get last ColNum 
		     
		     Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
		     
		     for(int i=0; i<RowNum-1; i++) //Loop work for Rows
		     {  
		          XSSFRow row= worksheet.getRow(i+1);
		     
		          for (int j=0; j<ColNum; j++) //Loop work for colNum
		          {
		        	  if(row==null)
		        		  Data[i][j]= "";
		        	  else 
		        	  {
		        		  XSSFCell cell= row.getCell(j);
		        		  if(cell==null)
		        			  Data[i][j]= ""; //if it get Null value it pass no data 
		        		  else
		        		  {
		        			  String value=formatter.formatCellValue(cell);
		        			  Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
		        		  }
		        	  }
		          }
		     }
		 
		     return Data;
	}
}


