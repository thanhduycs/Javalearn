package codility.chap5;

//you can also use imports, for example:
//import java.util.*;

//you can use System.out.println for debugging purposes, e.g.
//System.out.println("this is a debug message");

import static org.junit.Assert.*;

import org.junit.*;

//https://codility.com/demo/results/demoTQN45W-NKQ/

public class SolutionFish {
	public int solution(int[] A, int[] B) {
		final int UP_STREAM = 0;
		final int DOWN_STREAM = 1;
		
		int[] stacks = new int[A.length * 2];
		int stack_size = 0;

		for (int i = 0; i < A.length; i++) {
			if (stack_size == 0 || stacks[stack_size - 1] == B[i] // same
																	// direction
					|| B[i] == DOWN_STREAM) {
				// System.out.printf("%d push by \n", A[i]);
				stacks[stack_size++] = A[i]; // push size
				stacks[stack_size++] = B[i]; // push direction
			} else {
				int a = A[i]; // this fish
				while (stack_size > 0 && stacks[stack_size - 1] == DOWN_STREAM // downstream
						&& stacks[stack_size - 2] < a) {
					// System.out.printf("%d be eatean by %d\n",
					// stacks[stack_size-2], a);
					stack_size -= 2; // pop from stack
				}
				// no fish on stack bigger than this fish
				if (stack_size == 0 || stacks[stack_size - 1] == UP_STREAM) {
					stacks[stack_size++] = A[i]; // push size
					stacks[stack_size++] = B[i]; // push direction
				}
			}
		}
		// for (int i=0; i<stack_size; i+=2)
		// {
		// System.out.printf("%d, %d\n",
		// stacks[i],
		// stacks[i+1]);
		// }
		return stack_size / 2;
	}
	
	@Test
	public void testFunction()
	{
		int [] A = {4, 3, 2, 1, 5};
		int [] B = {0, 1, 0, 0, 0};
		int result = this.solution(A, B);
		
		assertEquals(result, 2);
	}
	
}
