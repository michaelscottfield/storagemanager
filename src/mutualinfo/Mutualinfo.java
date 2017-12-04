package mutualinfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

import kevin.zhang.NLPIR;
import preprocess.*;
public class Mutualinfo {
	/* 计算互信息
	 * @param 两个词语
	 */
	public double minfo(String word1, String word2, String filepath) {
		Readexcel readfromexcel = new Readexcel();
		File traindata = new File(filepath);
		String content;
		int count1 = 0;
		int count2 =0;
		int themecount = 0;
		int emotioncount = 0;
		try {
			List<Comment> comments = readfromexcel.readexcel(traindata);
			for(Comment comment : comments) {
				content = comment.content;
				count1 += StringUtils.countMatches(content, word1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/*直接计算互信息
	 * 
	 */
	public double calminfo(double probabilty[][], double pword1[], double pword2[],int len1, int len2) {
		double info = 0.0;
		for(int i = 0; i < len1; i++) {
			for(int j = 0; j < len2; j++) {
				info += probabilty[i][j] * Math.log10(probabilty[i][j] / pword1[i] / pword2[j]) / Math.log10(2);
			}
		}
		return info;
		
	}
	
	/*对计算互信息所需要的概率信息进行统计
	 * 
	 */
	public double infoforwords(List<Comment> comments, String word1, String word2) {
		double count = 0.0;//
		double count1 = 0.0;
		double count2 = 0.0;
		double crosscount = 0.0;
		String content;
		try {
			//NLPIR testNLPIR = new NLPIR();
		    for(Comment comment : comments) {
				content = comment.content;
				//byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(content.getBytes("utf8"), 1);
				count += 1.0;
				if(content.contains(word1)) count1 += 1.0;
				if(content.contains(word2)) count2 += 1.0;
				if(content.contains(word1) && content.contains(word2)) crosscount += 1.0;
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		double probxy = crosscount / count1;
		double probx = count1 / count;
		double proby = count2 / count;
		return probxy * Math.log10(probxy / probx / proby) / Math.log(10);
	}
	
	/*
	 * 
	 */
	public int info_polar(List<Comment> comments, String word1) {
		double count = 0;
		double wordcount = 0;
		double poscount = 0;
		double neucount = 0;
		double negcount = 0;
		double poscrossct = 0;
		double neucrossct = 0;
		double negcrossct = 0;
		double posentropy = 0;
		double neuentropy = 0;
		double negentropy = 0;
		String polarities[];
		String emowords[];
		try {
			for(Comment comment : comments) {
				if(comment.sentiment_anls == null || comment.sentiment_word == null) continue;
				polarities = comment.sentiment_anls.split(";");
				emowords = comment.sentiment_word.split(";");
				for(String polar : polarities) {
					if(polar.equals("1")) poscount += 1;
					if(polar.equals("0")) neucount += 1;
					if(polar.equals("-1")) negcount += 1;
				}
				for(int i = 0; i < emowords.length; i ++) {
					count += 1;
					if(emowords[i].equals(word1)) {
						wordcount += 1;
						//完全没有进入这个循环？？
						//输出的polarities几乎都是连续的1,1,1,1,1,...,-1,-1,-1,-1,...说明一个词往往都是只有一种极性
						//System.out.println(polarities[i]);
						if(polarities[i].equals("1")) poscrossct +=1;
						else if(polarities[i].equals("0")) neucrossct += 1;
						else if(polarities[i].equals("-1")) negcrossct += 1;
					}
				}
				
			}
			//输出的count全是43300????
			//count是训练数据里面所有的情感词
			//System.out.println(count);
			//System.out.println(neucrossct);
			//poscount == 25086
			//System.out.println(poscount);
			//poscrossct全是0.0???
			//System.out.println(poscrossct);
			//返回的info_polar全是0？？？
			
			double posprobxy = poscrossct / count;
			double posprobx = wordcount / count;
			double posproby = poscount / count;
			double neuprobxy = neucrossct / count;
			double neuproby = neucount / count;
			double negprobxy = negcrossct /count;
			double negproby = negcount / count;
			if(posprobx > 0 && posproby > 0) posentropy = posprobxy * Math.log(posprobxy / posprobx / posproby) / Math.log(10);
			if(posprobx > 0 && neuproby > 0) neuentropy = neuprobxy * Math.log(neuprobxy / posprobx / neuproby) / Math.log(10);
			if(posprobx > 0 && negproby > 0) negentropy = negprobxy * Math.log(negprobxy / posprobx / negproby) / Math.log(10);
			//posentropy neuentropy negentropy三个之间往往有两个都是NaN???
			//neuentropy和negentropy经常是NaN???
			//log0应该是NaN
			//log0是-Infinity
			//System.out.println(Math.log(0));
			//System.out.println(posentropy + " " + neuentropy + " " + negentropy);
			//if(negentropy > neuentropy) System.out.println("love is love");
			//negentropy > neuentropy的情况竟然不存在？？？
			if(posentropy > Math.max(neuentropy, negentropy)) {
				//max(neuentropy, negentropy)都是0????
				//System.out.println(Math.max(neuentropy, negentropy));
				return 1;
			}
			else if(negentropy >= neuentropy) {
				//为何从来没进入这个循环？？？
				//System.out.println("love is you");
				return -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
}
