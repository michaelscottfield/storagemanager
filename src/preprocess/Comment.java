package preprocess;

public class Comment {
	public int row_id;
	public String content;
	public String theme;
	public String sentiment_word;
	public String sentiment_anls;
	public void setRow_id(int x) {
		row_id = x;
	}
	public void setContent(String x) {
		content = x;
	}
	public void setTheme(String x) {
		theme = x;
	}
	public void setSentiment_word(String x) {
		sentiment_word = x;
	}
	public void setSentiment_anls(String x) {
		sentiment_anls = x;
	}
}
