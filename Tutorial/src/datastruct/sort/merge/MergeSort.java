package datastruct.sort.merge;

public class MergeSort {
	
	private int countMergeSort = 0;
	private int countMerge = 0;
	private int countElement = 0;
	
	public void mergeSort(int[] array) {
		this.countMerge = 0;
		this.countMergeSort = 0;
		this.countElement = array.length;
		int[] atemp = new int[array.length];
		mergeSort(array, atemp, 0, array.length);
	}

	private void mergeSort(int[] array, int[] atemp, int begin, int end) {
		this.countMergeSort++;
		if (end - begin < 2) {
			return;
		}
		int middle = (begin + end) / 2;
		mergeSort(array, atemp, begin, middle);
		mergeSort(array, atemp, middle, end);
		merge(array, atemp, begin, middle, end);
	}

	private void merge(int[] array, int[] atemp, int begin, int middle, int end) {
		this.countMerge++;
		int first_seg = begin;
		int second_seg = middle;
		for (int i = begin; i < end; i++) {
			if (first_seg >= middle) {
				atemp[i] = array[second_seg++];
			} else if (second_seg >= end) {
				atemp[i] = array[first_seg++];
			} else if (array[first_seg] <= array[second_seg]) {
				atemp[i] = array[first_seg++];
			} else {
				atemp[i] = array[second_seg++];
			}
		}

		for (int i = begin; i < end; i++) {
			array[i] = atemp[i];
		}
		if (first_seg + second_seg - 2 != end - begin) {
			// System.out.println(String.format("%d - %d vs %d - %d",
			// first_seg, second_seg, end, begin));
			// throw new RuntimeException("Wrongggggggggggggggggg");
		}
	}
	
	public void printStat()
	{
		System.out.println(String.format("elements = %d, countMerge = %d, countMergeSort = %d", 
				this.countElement, this.countMerge, this.countMergeSort));
	}

	public static void main(String[] args) {
		MergeSort mergeSorter = new MergeSort();
		int[] array = new int[100];
		
		for (int i =0; i<array.length; i++)
		{
			array[i] = (int)(Math.random() * 100000);
		}

		int checksum = 0;
		for (int element : array) {
			checksum ^= element;
		}

		// array = new int[]{3,2,1};
		mergeSorter.mergeSort(array);

		int c = 0;
		int reverify = 0;
		for (int element : array) {
			reverify ^= element;
			System.out.print(element + ", ");
			if (++c % 20 == 0) {
				System.out.println(element);
			}
		}
		System.out.println();

		if (checksum != reverify) {
			System.out.println("Input and output data different");
		} else {
			System.out.println("Your program run successful");
		}
		
		mergeSorter.printStat();
	}
}
