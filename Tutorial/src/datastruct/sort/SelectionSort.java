package datastruct.sort;

public class SelectionSort {
	
	public void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
		
	public void sort(int[] array) {
		for(int i=0; i<array.length-1; i++)
		{
			int sel = -1;
			int minimal = array[i];
			for (int j = i+1; j<array.length; j++) {
				if (array[j] < minimal)
				{
					minimal = array[j];
					sel = j;
				}
			}
			if (sel >= 0)
				swap(array, i, sel);
		}
	}
	
	public static void main(String[] args) {
		SelectionSort sorter = new SelectionSort();
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
		sorter.sort(array);

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
		
		
	}
}
