package buffermanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
				//需要初始化所有的file
				
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
		//需要init所有的files
		//考虑在data.dbf到底需要写些什么东西？？
		
	}
	public static void main(String args[]) {	
		createFile("data.dbf");//file storage
		//需要从data-5w-50w-zipf.txt读取所有需要的读写操作
		File projectdata = new File("data-5w-50w-zipf.txt");
		FileReader fr = null;
		int ops[] = new int[500000];
		int pageids[] = new int[500000];
		DSMgr disk_client = new DSMgr();
		
		try {
			fr = new FileReader(projectdata);
			BufferedReader br = new BufferedReader(fr);
			try {
				int ins_index = 0;
				String line = "..";
				while(line != null) {
					line = br.readLine();
					if(line == null) break;
					//System.out.println(line);
					String[] instructs = line.split(",");
					if(instructs[0].equals("0")) {//read
						ops[ins_index] = 0;
					}
					else if(instructs[0].equals("1")) {//write
						ops[ins_index] = 1;
					}
					pageids[ins_index] = Integer.parseInt(instructs[1]);
					//System.out.println(instructs[1]);
					ins_index += 1;
					//System.out.println("index" + ins_index);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disk_client.init();
		disk_client.OpenFile("data.dbf");
		
		//System.out.println(pageids[499999]);
		//System.out.println(ops[49999]);
		
		disk_client.CloseFile();
		return;
	} 
}
