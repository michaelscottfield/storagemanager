package buffer;

public class BCB {
	public int page_id;//һ��frame��һ��ҳ
	public int frame_id;
	public int latch;//��ʾ��Ӧ��frame�Ƿ��ѱ�ռ�� page_latch
	public int count;//fix count
	//����fix count������LRU
	public int dirty;
	/**
	 * 
	 */
	public void replacepage() {
		
	}
	
	
}
