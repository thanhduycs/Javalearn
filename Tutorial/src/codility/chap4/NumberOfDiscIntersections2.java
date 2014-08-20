package codility.chap4;

import java.util.*;

class Circle implements Comparable<Circle> {
	long edge;
	int index;

	Circle(long e, int i) {
		edge = e;
		index = i;
	}

	long getRightAssumingEdgeIsLeft() {
		return (long) (2 * index - edge + 1);
	}

	public int compareTo(Circle other) {
		return Long.valueOf(edge).compareTo(other.edge);
	}
}

public class NumberOfDiscIntersections2 {
	public int number_of_disc_intersections(int[] A) {
		int N = A.length;
		if (N < 2)
			return 0;
		int result = 0;

		SortedSet<Circle> leftEdges = new TreeSet<Circle>();
		for (int i = 0; i < N; i++) {
			leftEdges.add(new Circle((long) (i - A[i]), i));
		}
		int counter = 0;
		for (Circle c : leftEdges) {
			long rightEdge = c.getRightAssumingEdgeIsLeft();
			Circle dummyCircle = new Circle(rightEdge, -1);
			SortedSet<Circle> head = leftEdges.headSet(dummyCircle);
			result += head.size() - counter;
			if (result > 10000000)
				return -1;
			counter++;
		}
		return result;
	}
}