package datastruct.search.jtest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import datastruct.search.BinarySearch;

@RunWith(Parameterized.class)
public class TestBinarySearch {
	private int testSize;
	private BinarySearch searcher;
	

	public TestBinarySearch(int size) {
		this.testSize = size;
	}

	@Before
	public void initialize() {
		long startTime = System.currentTimeMillis();
		this.searcher = new BinarySearch(this.testSize, true);
		long endTime = System.currentTimeMillis(); 
		System.out.println(String.format("%d need %d miliseconds to initialize", 
				this.testSize, (int)(endTime-startTime)));
	}

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection testSize() {
		return Arrays.asList(new Object[][] { 
				{ 100 },
				{ 500 }, 
				{ 1000 }, 
				{ 10000 },
				{ 100000 },
				{ 1000000 },
				{ 10000000 },
				});
	}
	
	@Test
	public void testSearch()
	{
		long startTime = System.currentTimeMillis();
		int indexSample = (int)(Math.random()*1999) % this.testSize;
		int findValue = searcher.getItem(indexSample);
		int x = searcher.search(findValue);
		searcher.printArr(x-5, 15, x);
		long endTime = System.currentTimeMillis(); 
		System.out.println(findValue + " --> " + x);
		System.out.println(String.format("Array size %d need %d steps during %d miliseconds",
				this.testSize, this.searcher.countStep,
				(int)(endTime-startTime)));
	}
}
