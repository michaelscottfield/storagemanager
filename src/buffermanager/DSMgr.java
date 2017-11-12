package buffermanager;

import java.io.File;

import buffer.Bframe;

/*
 * 负责管理磁盘
 */
public class DSMgr {
	public static int MAXPAGES = 50000;//题中所设的最大数量
	/*
	 * 读写文件
	 * @return error code
	 */
	public int OpenFile(String filename) {
		return 0;
		
	}
	
	/* close the file that is in current use
	 * @return error code
	 */
	public int CloseFile() {
		return 0;
		
	}
	
	/*
	 * 
	 */
	public Bframe ReadPage(int page_id) {
		return null;
		
	}
	
	/*
	 * 
	 */
	public int WritePage(int frame_id, Bframe frm) {
		return frame_id;
		
	}
	
	/* move the file pointer
	 * 
	 */
	public int Seek(int offset, int pos) {
		return pos;
		
	}
	
	/*
	 * return current file
	 */
	public File GetFile() {
		return null;
		
	}
	
	/*
	 * 
	 */
	public void IncNumPages() {
		
	}
	
	/*
	 * @function increment the page counter
	 */
	public int GetNumPages() {
		return 0;
		
	}
	
	/*
	 * @function keep track of the pages that are being used
	 */
	public void SetUse(int index, int use_bit) {
		
	}
	
	/*
	 * 
	 */
	public int GetUse(int index) {
		return index;
		
	}
	
	private File currFile;
	private int numPages;
	private int pages[] = new int[MAXPAGES];
}
