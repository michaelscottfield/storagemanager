package buffermanager;

import java.io.File;
import java.util.HashMap;
public class Client {
	/*
	 * 创建文件
	 * @param fileName 文件内容
	 */
	public static boolean createFile(String fileName) {
		Boolean bool = false;
		File file = new File(fileName);
		try {
			//如果文件不存在，则创建新的文件
			if(!file.exists()) {
				file.createNewFile();
				bool = true;
				System.out.println("success create file,the file is " + fileName);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/*
	 * @function materialize all pages in the disk
	 * @param the only one physical file
	 */
	public void init(String filename) {
		
	}
	public static void main(String args[]) {	
		createFile("data.dbf");//file storage
		
	} 
}
