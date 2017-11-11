package test;

import java.util.*;
import java.io.*;

//import zlz.Tool.Split;


public class Test {
	
	
	public static void main(String[] args) {
		
		try {
//			int filecount = 0;
//			int termcount = 0;
//			int totalcount = 0;
//			HashMap<String, Integer> dim2count = new HashMap<String, Integer>();
//			HashMap<String, Integer> dim2totalcount = new HashMap<String, Integer>();
//			
//			File dir = new File("E:\\TaobaoSentiment\\TaobaoData\\Data\\DigreeTFAdj\\Result"); 
//			File[] files = dir.listFiles();
//			for(File infile : files) {
//				if(infile.getName().contains("bak"))
//					continue;
//				int dimtermcount = 0;
//				int dimtotalcount = 0;
//				FileReader reader = new FileReader(infile);
//				BufferedReader rd = new BufferedReader(reader);
//				String s = "";
//				
//				int line = 0;
//				while((s = rd.readLine())!=null) {
//					if(++line==1 && !s.contains(" "))
//						break;
//					else if(line == 1)
//						filecount++;
//					String[] sp = s.split(" ");
//					if(sp.length != 2)
//						continue;
//					double pol = Double.parseDouble(sp[1]);
//					if(pol != 0) {
//						termcount++;
//						dimtermcount++;
//					}
//					totalcount++;
//					dimtotalcount++;
//				}
////				if(dimtermcount == 0)
////					filecount--;
//				dim2totalcount.put(infile.getName(), dimtotalcount);
//				dim2count.put(infile.getName(), dimtermcount);
//			}
//			
//			File outfile = new File("E:\\TaobaoSentiment\\TaobaoData\\Data\\stats.txt");
//			FileOutputStream out = new FileOutputStream(outfile);
//			PrintStream p = new PrintStream(out);
//			p.println("File Count: " + filecount);
//			p.println("Term Count: " + termcount);
//			p.println("Total Count: " + totalcount);
//			p.println((double)termcount / filecount);
//			p.println((double)totalcount / filecount);
//			List<String> sortdim = new ArrayList<String>();
//			for(String dim : dim2count.keySet())
//				sortdim.add(dim);
//			for(int i = 0;i<sortdim.size();i++) {
//				for(int j = i+1;j<sortdim.size();j++) {
//					String di = sortdim.get(i);
//					String dj = sortdim.get(j);
//					int ci = dim2count.get(di);
//					int cj = dim2count.get(dj);
//					if(ci > cj) {
//						sortdim.set(i, dj);
//						sortdim.set(j, di);
//					}
//				}
//			}
//			for(String dim : sortdim)
//				p.println(dim + "\t" + dim2count.get(dim) + "\t" + dim2totalcount.get(dim));
			
			File infile = new File("E:\\TaobaoSentiment\\TaobaoData\\Data\\Original\\sample_res_attempt_201210311519_2094568_m_000011_0.txt");
			FileReader reader = new FileReader(infile);
			BufferedReader rd = new BufferedReader(reader);
			String s = "";
			int c1 = 0;
			int c0 = 0;
			int c_1 = 0;
			while((s = rd.readLine())!=null) {
				if(s.startsWith("1\t"))
					c1++;
				else if(s.startsWith("0\t"))
					c0++;
				else if(s.startsWith("-1\t"))
					c_1++;
			}
			System.out.println(c1);
			System.out.println(c0); 
			System.out.println(c_1);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void encodingtest() throws Exception {
		File f= new File("11.csv");
		File f2 = new File("11.txt");
//		BufferedReader rd = new BufferedReader(new InputStreamReader(  
//                new FileInputStream(f2), "utf8"));
		BufferedReader rd = new BufferedReader(new FileReader(f2));
		String s = "";
		String text = "";
		while((s = rd.readLine())!=null) {
			System.out.println(s.length());
			text = s;
			System.out.println(text);
		}
//		String s = "我爱北京天安门";
		FileOutputStream out = new FileOutputStream(f);
		PrintStream p = new PrintStream(out);
		text = new String(text.getBytes(), "gbk");
		System.out.println(text);
//		s = new String(s.getBytes(), "gb2312");
		System.out.println(text);
		p.println("test1," + text);
		
	}
	
	public static void getSampleAll() throws Exception {
		File inf = new File("D:\\TaobaoSentiment\\TaobaoData\\Result\\ModifyRelation\\firstofsentence\\pol9.txt");
		FileReader reader = new FileReader(inf);
		BufferedReader rd = new BufferedReader(reader);
		
		File outf = new File("D:\\TaobaoSentiment\\TaobaoData\\Result\\ModifyRelation\\firstofsentence\\sample_pol9.txt");
		FileOutputStream out = new FileOutputStream(outf);
		PrintStream p = new PrintStream(out);
		
		Random rdm = new Random();
		rdm.setSeed(System.currentTimeMillis());
		Set<Integer> number = new HashSet<Integer>();
		for(int i = 0;i<1000;i++) {
			int n = rdm.nextInt(400000);
			System.out.println(n);
			if(number.contains(n)) i--;
			else number.add(n);
		}
		
		String s = "";
		int line = 0;
		while((s = rd.readLine())!=null) {
			if(number.contains(line++)) p.println(s);
		}
		p.close();
		out.close();
		rd.close();
		reader.close();
		
	}
	
	public static void getSampleWrong() throws Exception {
		File outf = new File("D:\\TaobaoSentiment\\TaobaoData\\Result\\ModifyRelation\\firstofsentence\\result_sample_wrong.txt");
		FileOutputStream out = new FileOutputStream(outf, true);
		PrintStream p = new PrintStream(out, false);
		
		p.println("*******************************");
		
		for(int i = 9;i<=11;i++) {
			File inf = new File("D:\\TaobaoSentiment\\TaobaoData\\Result\\ModifyRelation\\firstofsentence\\result_sample_pol" + i + ".txt");
			FileReader reader = new FileReader(inf);
			BufferedReader rd = new BufferedReader(reader);
			String s = "";
			while((s = rd.readLine())!=null) {
				if(s.contains("w ")) p.println(s);
			}
			rd.close();
			reader.close();
		}
		p.close();
		out.close();
		
	}
	

}
