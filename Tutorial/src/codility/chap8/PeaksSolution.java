package codility.chap8;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class PeaksSolution {

	public void printArr(int[] A) {
		if (A.length > 100)
			return;
		System.out.print("{");
		for (int i = 0; i < A.length - 1; i++) {
			System.out.printf("%2d, ", A[i]);
		}
		System.out.println(A[A.length - 1] + "}");
	}

	public ArrayList<Integer> findDevisor(int n, int max_size)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int root = (int) Math.sqrt(n);
        for(int i=2; i<=root; i++) {
            if (n%i == 0)
            {
                int b = n / i;
                if (i <= max_size)
                    arrayList.add(i);
                if (b <= max_size)
                    arrayList.add(b);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }
    
    public ArrayList<Integer> findPeaks(int[] A)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        
        for(int i=1; i<A.length-1; i++)
        {
            if (A[i] > A[i-1] && A[i] > A[i+1])
            {
                arrayList.add(i);
            }
        }
        return arrayList;
    }
    
    public int findMaxDivided(int[] A, int n)
    {
        return 0;
    }
    
    public int solution(int[] A) {
        ArrayList<Integer> peaks = findPeaks(A);
        ArrayList<Integer> divisors = findDevisor(A.length, peaks.size());
        
        for(Integer d : divisors)
        {
            System.out.println(d);
        }
        
        System.out.println(";;--;;");
        for(Integer d : peaks)
        {
            System.out.println(d);
        }
        return 0;
    }

	@Test
	public void testCase1() throws Exception {
		int[] arr = { 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
		int result = solution(arr);

		assertEquals(result, 1);

	}
}
