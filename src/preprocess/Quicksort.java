package preprocess;
/*
 * ¿ìËÙÅÅĞò
 */
public class Quicksort {
	
	public int partition(double[]data, int low, int high) {
		double key = data[low];
		while(low < high) {
			while(low < high && data[high] >= key) {
				high --;
			}
			data[low] = data[high];
			while(low < high && data[low] <= key) {
				
				low ++;
			}
			data[high] = data[low];
		}
		data[low] = key;
		return low;
	}
	
	public double[] quicksort(double[] data, int low, int high) {
		if(low < high) {
			int result =  partition(data, low ,high);
			quicksort(data, low, result - 1);
			quicksort(data, result + 1, high);
		}
		return data;
	}
}
