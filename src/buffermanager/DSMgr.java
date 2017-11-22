package buffermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import buffer.BCB;
import buffer.Bframe;
import buffer.Page;


/*
 * 负责管理磁盘
 */
public class DSMgr {
	FileInputStream fis = null;
	public String filename = "data.dbf";
	File currentFile = new File(filename);
	public RandomAccessFile raf;
	//try {
	//public RandomAccessFile raf = new RandomAccessFile(currentFile, "rw");
	//} catch (Exception e) {
		
	//};
	public int pcounter;
	
	public static int MAXPAGES = 50000;//题中所设的最大数量
	//public Page pages;
	public Page pages[] = new Page[MAXPAGES];
	
	/*
	 * 
	 */
	public void init() {
		
	}
	
	/*
	 * 读写文件
	 * @return error code
	 */
	public int OpenFile(String filename) {
		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/* close the file that is in current use
	 * @return error code
	 */
	public int CloseFile() {
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/*
	 * @detail this function calls fseek() and fread() to gain data from a file 
	 */
	public Bframe ReadPage(int page_id) {
		//boolean byteread = false;
		try {
			boolean byteread;
			while(byteread = fis.read() != -1) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page rpage = pages[page_id];
		
		return null;
		
	}
	
	/*
	 * @return 
	 */
	public int WritePage(int frame_id, Bframe frm) {
		//when a page is taken out of the buffer,must write it to the disk
		BCB bcb = frm.bcontrolb;
		int pageid = bcb.page_id;
		Page page = pages[pageid];
		page.use_bit = 0;
		return frm.field.length;//return how many bytes were written
		
	}
	
	/* move the file pointer
	 *@param pos为原来的指针位置 
	 */
	public int Seek(int offset, int pos) {
		try {
			raf.seek(pos + offset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (offset + pos);
		
	}
	
	/*
	 * return current file
	 */
	public File GetFile() {
		
		return currentFile;
		
	}
	
	/*
	 * @function increment the page counter
	 */
	public void IncNumPages() {
		pcounter += 1;
	}
	
	/*
	 * @return the page counter
	 */
	public int GetNumPages() {
		return pcounter;
		
	}
	
	/*
	 * @function keep track of the pages that are being used
	 */
	public void SetUse(int index, int use_bit) {
		pages[index].use_bit = use_bit;
	}
	
	/*
	 * 
	 */
	public int GetUse(int index) {
		
		return pages[index].use_bit;
		
	}
	
	//private File currFile;
	//private int numPages;
	
}
