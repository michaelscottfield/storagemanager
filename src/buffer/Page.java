package buffer;

/*
 * the only mattered information is the page_id and page_size
 */
public class Page {
	public int page_size;
	public int use_bit;
	public int page_id;
	public boolean in_buffer;
}
