package progpearls.chap2;

public class Problem3Rostate {
	
	public void print(char[] array)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (char c: array)
		{
			stringBuilder.append(c);
		}
		System.out.println(stringBuilder.toString());
	}
	
	public void solve(char[] array, int rostateAt)
	{
		char temp;
		for(int i=0; i<array.length; i++)
		{
			int newloc = (i + array.length - rostateAt) % array.length;
			temp = array[i];
			array[i] = array[newloc];
			array[newloc] = temp;
		}
		print(array);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String in = "abcdefgh";
		new Problem3Rostate().solve(in.toCharArray(), 3);
	}

}
