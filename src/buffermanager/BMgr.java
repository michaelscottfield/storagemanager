package buffermanager;

import java.util.Arrays;

import buffer.BCB;
import buffer.Bframe;
import buffer.Page;

public class BMgr {
	public int defbufsize = 1024;
	
	public Bframe bframe = new Bframe();
	public DSMgr dsmgr = new DSMgr();
	public BCB[] bufferblocks = new BCB[defbufsize];
	private int ftop[] = new int[defbufsize];//map frame_ids to page_ids
	private BCB ptof[] = new BCB[defbufsize];//map page_ids to frame_ids and BCB's
	/* @param page_id protection 
	 * @return frame_id
	 */
	public int FixPage(int page_id, int prot) {
		//首先判断该page是否已经在buffer之中
		boolean inbuffer = false;
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.page_id == page_id) {
				inbuffer = true;
				return bufferblock.frame_id;
			}
		}
		//
		if(!inbuffer) {
			int free_frameid = SelectVictim();
			for(BCB bufferblock : bufferblocks) {
				if(bufferblock.frame_id == free_frameid) {
					if(bufferblock.dirty == 1) {
						
					}
					bufferblock.count += 1;
					break;
				}
			}
		}
		return prot;
		
	}
	/* 
	 * @return a page_id and a frame_id
	 */
	public String FixNewPage() {
		BCB tempblock = null;
		for(BCB bcb : bufferblocks) {
			if(bcb.latch == 0) {
				tempblock = bcb;
				break;
			}
		}
		Page temppage = null;
		for(Page page : dsmgr.pages) {
			if(page.in_buffer == false) {
				temppage = page;
				break;
			}
		}
		return temppage.page_id + "," +  tempblock.frame_id;
	}
	/*
	 * 
	 */
	public int UnfixPage(int page_id) {
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.page_id == page_id) {
				bufferblock.count -= 1;
				if(bufferblock.count == 0)	bufferblock.latch = 0;
				if(bufferblock.frame_id == SelectVictim()) {
					//remove frame
					
				}
			}
		}
		return page_id;
		
	}
	/*
	 * @return the number of buffer pages that are free to be used
	 */
	public int NumFreeFrames() {
		int count = 0;
		//遍历所有的buffer control blocks，找到所有的free frames
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.latch == 0) count += 1;
		}
		return count;
		
	}
	
	/* 
	 * 
	 */
	public int FindFrame(int page_id) {
		for(BCB bcb : bufferblocks) {
			if(bcb.page_id == page_id) {
				return bcb.frame_id;
			}
		}
		return 0;
		
	}
	
	/* @function select a frame to replace
	 * 脏页需要被写到磁盘上
	 */
	public int SelectVictim() {
		int count = 60000;
		int frameid = 0;
		//select the least recently used frame
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.count < count) {
				count = bufferblock.count;
				frameid = bufferblock.frame_id;
			}
		}
		return frameid;
		
	}
	
	/* @param page_id
	 * @return frame_id
	 */
	public int Hash(int page_id) {
		return (page_id)%defbufsize;
		
	}
	
	/* @function remove BCB for the page_id from the array
	 * this is only called if the selectvictim() function needs to replace a frame
	 */
	public void RemoveBCB(int page_id) {
		int index = 0;
		for(BCB bcb : bufferblocks) {
			if(bcb.page_id == page_id) {
				index = Arrays.binarySearch(bufferblocks, bcb);
				break;
			}
		}
		
		for(int i = index; i < bufferblocks.length; i ++) {
			bufferblocks[i] = bufferblocks[i + 1];
		}
	}
	
	/* removes the least-recently-used element from the list
	 * 
	 */
	public void RemoveLRUEle(int frid) {
		for(BCB bcb : bufferblocks) {
			if(bcb.frame_id == frid) {
				bcb.latch = 0;
				//bcb.page_id = 0;
				return;
			}
		}
	}
	
	/* set the  dirty bit for the frame_id
	 * 
	 */
	public void SetDirty(int frame_id) {
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.frame_id == frame_id) {
				bufferblock.dirty = 1;
				return;
			}
		}
	}
	
	/* assign the dirty_bit for the corresponding frame_id to zero
	 * 
	 */
	public void UnsetDirty(int frame_id) {
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.frame_id == frame_id) {
				bufferblock.dirty = 0;
				return;
			}
		}
	}
	
	/* write out any pages that are still in the buffer that may need to be written
	 * 
	 */
	public void WriteDirtys() {
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.dirty == 1) {
				dsmgr.WritePage(bufferblock.frame_id, bframe);
			}
		}
	}
	
	/* print out the contents of the frame described by the frame_id
	 * 
	 */
	public void PrintFrame(int frame_id) {
		for(BCB bufferblock : bufferblocks) {
			if(bufferblock.frame_id == frame_id) {
				System.out.println("frame_id : " + bufferblock.frame_id);
				System.out.println("page_id : " + bufferblock.page_id);
				System.out.println("latch : " + bufferblock.latch);
				System.out.println("count : " + bufferblock.count);
				return;
			}
		}
	}
	
	/*
	 * two hash tables
	 */
	
}
