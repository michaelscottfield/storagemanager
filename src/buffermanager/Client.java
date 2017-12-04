package buffermanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
				//��Ҫ��ʼ�����е�file
				
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
		//��Ҫinit���е�files
		//������data.dbf������ҪдЩʲô��������
		
	}
	public static void main(String args[]) {	
		createFile("data.dbf");//file storage
		//��Ҫ��data-5w-50w-zipf.txt��ȡ������Ҫ�Ķ�д����
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
