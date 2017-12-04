package buffer;
/*
 * 一个frame里面放一个page
 */
public class Bframe {
	public static int framesize = 4096;
	public char field[] = new char[framesize];
	public BCB bcontrolb;
}
