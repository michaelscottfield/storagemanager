package buffermanager;

import java.io.File;
import java.util.HashMap;
public class Client {
	/*
	 * �����ļ�
	 * @param fileName �ļ�����
	 */
	public static boolean createFile(String fileName) {
		Boolean bool = false;
		File file = new File(fileName);
		try {
			//����ļ������ڣ��򴴽��µ��ļ�
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
