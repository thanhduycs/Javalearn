package datastruct.sort;

public class BubbleSort extends Sorter {

	public void sort(int[] array) {

		boolean flag = true;
		while(flag)
		{
			flag = false;
			for(int i=0; i<array.length-1; i++)
			{
				if ((array[i] > array[i+1])) {
					swap(array, i, i+1);
					flag = true;
				}
			}
		}

	}
	
	public static void main(String[] args) {
		Sorter sorter = new BubbleSort();
		int[] array = new int[1000];
		
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
