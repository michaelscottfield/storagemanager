package preprocess;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;  
import java.text.DecimalFormat;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Readexcel {
	/**
	 * 读取excel文件
	 * @param file 文件路径
	 * @return 返回包含文件内容的list
	 * @throws IOException 
	 */
	public List<Comment> readexcel(File file) throws IOException {
		if (file == null) return null;
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		Comment comment = null;
		List<Comment> list = new ArrayList<Comment>();
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		int rowNum;
		
		for(rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if(xssfRow != null) {
				comment = new Comment();
				XSSFCell rowid = xssfRow.getCell(0);
				XSSFCell content = xssfRow.getCell(1);
				XSSFCell theme = xssfRow.getCell(2);
				XSSFCell sentimentword = xssfRow.getCell(3);
				XSSFCell sentimentanls = xssfRow.getCell(4);
				String temp = "";
				if(rowid.getCellTypeEnum() == CellType.NUMERIC) {
				temp  = temp += String.valueOf(new Double(rowid.getNumericCellValue()).intValue());
				}
				//else {
					//temp  = temp += String.valueOf(rowid.getStringCellValue());
				//}
				if(content.getCellTypeEnum() == CellType.NUMERIC) continue;
				if(content != null) temp  = temp += String.valueOf(content.getStringCellValue());
			    if(theme != null) temp  = temp += String.valueOf(theme.getStringCellValue());
			    if(sentimentword != null) temp  = temp += String.valueOf(sentimentword.getStringCellValue());
				if(sentimentanls != null) temp  = temp += String.valueOf(sentimentanls.getStringCellValue());
				//System.out.println(temp);
				//System.out.println(rowid.getStringCellValue() + " " + content.getStringCellValue() 
				//+ " " + theme.getStringCellValue() + " " + sentimentword.getStringCellValue() + " " 
					//	+ sentimentanls.getStringCellValue());
				
				comment.setRow_id((int)rowid.getNumericCellValue());
				if(content != null) comment.setContent(content.getStringCellValue());
				if(theme != null) comment.setTheme(theme.getStringCellValue());
				if(sentimentword != null) comment.setSentiment_word(sentimentword.getStringCellValue());
				if(sentimentanls != null) comment.setSentiment_anls(sentimentanls.getStringCellValue());
				list.add(comment);
			}
		}
		return list;
		}
		
		/*
		 * 
		 */
		public List<Comment> readcsv(File file){
			BufferedReader br = null;
			String line = "";
			List<Comment> list = new ArrayList<Comment>();
			String[] strs;
			Comment comment = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((line = br.readLine()) != null) {
					//System.out.println(line);
					//用第一个逗号分割
					strs = line.split(",");
					comment = new Comment();
					comment.setRow_id(Integer.parseInt(strs[0]));
					comment.setContent(strs[1]);
					list.add(comment);
					//System.out.println(strs[1]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
}
