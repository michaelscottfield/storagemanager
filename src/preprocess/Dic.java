package preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Map;
import java.util.Map.Entry;
public class Dic {//基于训练数据生成词典
	/**
	 * @param 训练数据
	 * @return 主题词语集合
	 */
	public void themedic(List<Comment> comments) {
		Set<String> themedict = new HashSet<String>();
		for(Comment comment : comments) {
			if (comment.theme == null) continue;
			String themes[] = comment.theme.split(";");
			for(String theme : themes) {
				if(!themedict.contains(theme))
					themedict.add(theme);
			}
		}
		File themedict1 = new File("F:\\projects\\eclipse-workspace\\CCFSentiment\\themedict.txt");
		if(themedict1.exists()&&themedict1.isFile()) {
			
		}
		else {
			try {
				//创建文件
				themedict1.createNewFile();
				System.out.println("创建themedict.txt");
			}catch(IOException e) {
				System.out.println("themedic文件创建失败，错误信息:" + e.getMessage());
				return;
			}
		}
		try {
			PrintWriter pw = new PrintWriter(themedict1);
			for(String theme : themedict) 
			{
				pw.write(theme + " themew\n");
			}
			pw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * @param 训练数据
	 * @return
	 */
	public void emotiondic(List<Comment> comments){
		//Set<String> posemotiondict = new HashSet<String>();
		double rate;
		//声明键值对对象
		Map<String, Double> posemorating = new HashMap<String, Double>();
		Map<String, Double> neuemorating = new HashMap<String, Double>();
		Map<String, Double> negemorating = new HashMap<String, Double>();
		for(Comment comment : comments) {
			if(comment.sentiment_word == null) continue;
			String emotions[] = comment.sentiment_word.split(";");
			String rating[] = comment.sentiment_anls.split(";");
			for(int i = 0; i < emotions.length; i++) {
				rate = Double.valueOf(rating[i]);
				if(rate == 1.0) {
					if(posemorating.containsKey(emotions[i]))//已经含有该情感关键词 
					{
						posemorating.put(emotions[i], posemorating.get(emotions[i]) + 1.0);
					}
					else {
						posemorating.put(emotions[i], 1.0);
					}
			    }
				else if(rate == 0.0) {
					if(neuemorating.containsKey(emotions[i]))//已经含有该情感关键词 
					{
						neuemorating.put(emotions[i], neuemorating.get(emotions[i]) + 1.0);
					}
					else {
						neuemorating.put(emotions[i], 1.0);
					}
			    }
				else if(rate == -1.0) {
					if(negemorating.containsKey(emotions[i]))//已经含有该情感关键词 
					{
						negemorating.put(emotions[i], negemorating.get(emotions[i]) + 1.0);
					}
					else {
						negemorating.put(emotions[i], 1.0);
					}
			    }
			}
			
		}
		
	//根据生成的数据集生成词典
	File posdic = new File("F:\\projects\\eclipse-workspace\\CCFSentiment\\emotiondict.txt");
	if(posdic.exists()&&posdic.isFile()) {
		
	}
	else {
		try {
			//创建文件
			posdic.createNewFile();
			System.out.println("创建emotiondict.txt");
		}catch(IOException e) {
			System.out.println("emotiondic文件创建失败，错误信息:" + e.getMessage());
			return;
		}
	}
	String word;
	double count;
	try {
		PrintWriter pw = new PrintWriter(posdic);
		Set<Entry<String, Double>>entrySet=posemorating.entrySet();
		for(Entry<String, Double> entry:entrySet){
			word = entry.getKey();
			count = entry.getValue();
			pw.write(word + " posemo\n");
		}
		Set<Entry<String, Double>>entrySet2=neuemorating.entrySet();
		for(Entry<String, Double> entry:entrySet2) {
			word = entry.getKey();
			count = entry.getValue();
			pw.write(word + " neuemo\n");
		}
		Set<Entry<String, Double>>entrySet3=negemorating.entrySet();
		for(Entry<String, Double> entry:entrySet3) {
			word = entry.getKey();
			count = entry.getValue();
			pw.write(word + " negemo\n");
		}
		pw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	return;
	}
	
	/*对词典进行合并
	 * 
	 */
	public void dic_merge(String filepaths[], int num) {
		BufferedReader br = null;
		
		int word_count = 0;
		String strs[];
		String line;
		try {
			for(int i = 0; i < num; i++) {
				File single_dic = new File(filepaths[i]);
				br = new BufferedReader(new FileReader(single_dic));
				while((line = br.readLine()) != null) {
					strs = line.split(" ");
					word_count += 1;
				}
			}
			String words[] = new String[word_count];
			String speech[] = new String[word_count];
			word_count = 0;
			for(int i = 0; i < num; i++) {
				File single_dic = new File(filepaths[i]);
				br = new BufferedReader(new FileReader(single_dic));
				while((line = br.readLine()) != null) {
					strs = line.split(" ");
					words[word_count] = strs[0];
					speech[word_count] = strs[1];
					word_count += 1;
				}
			}
			File dicfile = new File("F:\\projects\\eclipse-workspace\\CCFSentiment\\sentiment_dict.txt");
			PrintWriter pw = new PrintWriter(dicfile);
			for(int i =0; i < word_count; i ++) {
				pw.write(words[i] + " " + speech[i] + "\n");
			}
			pw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
