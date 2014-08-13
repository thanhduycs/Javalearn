package uva;

public class Prob1 {
	
	public static int cal3n1(int x)
	{
		if (x <= 1) {
			System.out.println(x + ";");
			return 1;
		}
		int c = 0;
		while(x%2==0) {
			x=x/2;
			System.out.print(x + ", ");
			c++;
		}
		if (x > 1)
		{
			x = 3 * x + 1;
			System.out.print(x + ", ");
			c++;
		}
		return c + cal3n1(x);
	}

	public static void main(String[] args) {
		int begin = Integer.parseInt(args[0]);
		int end = Integer.parseInt(args[1]);
		
		int a = begin, b = end;
		
		int max = 0;
		while (begin <= end)
		{
			int c = cal3n1(begin);
			if (max < c )
				max = c;
			begin++;
		}
		
		System.out.println(String.format("%d %d %d", a, b, max));

	}

}
