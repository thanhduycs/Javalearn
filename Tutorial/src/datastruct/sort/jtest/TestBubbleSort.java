package datastruct.sort.jtest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastruct.sort.BubbleSort;
import datastruct.sort.Sorter;

public class TestBubbleSort {
	
	private Sorter sorter;
	
	@Before
	public void initSorter()
	{
		this.sorter = new BubbleSort();
	}
	
	@After
	public void cleanSorter()
	{
		this.sorter = null;
	}
	
	@Test
	public void canInitSort()
	{
		assertNotNull(sorter);
	}
	
	@Test
	public void sort1Elements()
	{
		int[] unsort = {111};
		int[] inorder = {111};
		this.sorter.sort(unsort);
		assertArrayEquals(unsort, inorder);
	}
	
	@Test
	public void sort2Elements()
	{
		int[] unsort = {111,1};
		int[] inorder = {111,1};
		this.sorter.sort(unsort);
		assertArrayEquals(unsort, inorder);
	}
	
	@Test
	public void sort3Elements()
	{
		int[] unsort = {1,2,1,9,8,6,4,7};
		int[] inorder = {1,1,2,4,6,7,8,9};
		this.sorter.sort(unsort);
		assertArrayEquals(unsort, inorder);
	}
	
	
	@Test
	public void testBubbleSort()
	{
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
			System.out.println("Your program runs successful.");
		}
		
		assertEquals(checksum, reverify);
	}
	
	@Test(expected = NullPointerException.class)
	public void exceptionIsThrowIfAnArrayIsEmpty()
	{
		sorter.sort(null);
	}
	
}
