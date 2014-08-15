package datastruct.sort;

public class QuickSort2 extends Sorter {
	
	private void quickSort(int[] array, int low, int high)
	{
		if (array == null || array.length == 0)
			return;
 
		if (low >= high)
			return;
 
		//pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = array[middle];
 
		//make left < pivot and right > pivot
		int l = low, h = high;
		while (l <= h) {
			while (array[l] < pivot) {
				l++;
			}
 
			while (array[h] > pivot) {
				h--;
			}
 
			if (l <= h) {
				if (l < h) {
					int temp = array[l];
					array[l] = array[h];
					array[h] = temp;
				}
				l++;
				h--;
			}
		}
 
		//recursively sort two sub parts
		if (low < h)
			quickSort(array, low, h);
 
		if (high > l)
			quickSort(array, l, high);
	}
	
	public void sort(int[] array) {
		quickSort(array, 0, array.length-1);
	}
	
	public static void main(String[] args) {
		Sorter sorter = new QuickSort2();
		int[] array = new int[100000];
		
		
		for (int i =0; i<array.length; i++)
		{
			array[i] = (int)(Math.random() * 1000000);
		}
		
		array = new int[] { 9, 2, 4, 7, 3, 7, 10 };

		int checksum = 0;
		for (int element : array) {
			checksum ^= element;
		}

		// array = new int[]{3,2,1};
		sorter.sort(array);

		int reverify = print(array);
		verifyInOrder(array);

		if (checksum != reverify) {
			System.out.println("Input and output data different");
		} else {
			System.out.println("Your program run successful");
		}
	}

}
