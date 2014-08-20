package codility.chap5;

//https://codility.com/demo/results/demoVD7XYU-49S/

public class Nesting {
	public int solution(String S) {
		int count_open = 0;
		for (int i = 0; i < S.length(); i++) {
			char chr = S.charAt(i);
			if (chr == '(') {
				count_open++;
			} else {
				count_open--;
				if (count_open < 0)
					return 0;
			}
		}

		if (count_open != 0)
			return 0;

		return 1;
	}
}
