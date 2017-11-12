package buffer;

public class BCB {
	public int page_id;//一个frame存一个页
	public int frame_id;
	public int latch;//表示对应的frame是否已被占有 page_latch
	public int count;//fix count
	//根据fix count来进行LRU
	public int dirty;
	/**
	 * 
	 */
	public void replacepage() {
		
	}
	
	
}
