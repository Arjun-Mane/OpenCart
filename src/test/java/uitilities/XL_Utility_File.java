package uitilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL_Utility_File 
{
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    
    String path="";
    
    XL_Utility_File(String path)
    {
    	this.path=path;
    }
    
    public int GetRowCount(String SheetName) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	int trows=sheet.getLastRowNum();
    	
    	workbook.close();
    	fi.close();
    	return trows;
    }
    
    public int GetCellCount(String SheetName, int rownum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	row=sheet.getRow(rownum);
    	int tcells=row.getLastCellNum();
    	
    	workbook.close();
    	fi.close();
    	return tcells;
    }
    
    public String GetCellData(String SheetName, int rownum, int cellnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(cellnum);
    	
    	String data;
    	DataFormatter formatter=new DataFormatter();
    	
    	try
    	{
    	data=formatter.formatCellValue(cell);
    	}
    	catch(Exception e)
    	{
    		data="";
    	}
 	
    	workbook.close();
    	fi.close();
    	return data;
    }
    
    public void WriteDataIntoCell(String SheetName, int rownum, int cellnum, String data) throws IOException
    {
    	File xlfile=new File(path);
    	
    	if(!xlfile.exists())
    	{
    		fo=new FileOutputStream(path);
    		workbook=new XSSFWorkbook();
    		workbook.write(fo);
    		
    	}
    	
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook();
    	
    	if(workbook.getSheetIndex(SheetName)==-1);
    	   workbook.createSheet(SheetName);
    	   sheet=workbook.getSheet(SheetName);
    	   
    	if(sheet.getRow(rownum)==null);
    	   sheet.createRow(rownum);
    	   row=sheet.getRow(rownum);
    	   
    	  cell=row.createCell(cellnum);
    	  cell.setCellValue(data);
    	  
    	  fo=new FileOutputStream(path);
    	  workbook.write(fo);
    	  workbook.close();
    	  fi.close();
    	  fo.close();
    }
    
    public void FillGreanColore(String SheetName, int rownum, int cellnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(cellnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    }
    
    public void FillRedColore(String SheetName, int rownum, int cellnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(cellnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillBackgroundColor(IndexedColors.RED.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    }
}
