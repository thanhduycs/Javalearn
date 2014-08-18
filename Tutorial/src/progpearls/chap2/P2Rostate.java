package progpearls.chap2;

public class P2Rostate {
	
	public void print(char[] array)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (char c: array)
		{
			stringBuilder.append(c);
		}
		System.out.println(stringBuilder.toString());
	}
	
	
	public void solveNN(char[] array, int n)
	{
		print(array);
		char[] buffer = new char[3];
		while(n > 0)
		{
			int  i = 0;
			while(i < buffer.length && n-- > 0)
			{
				buffer[i] = array[i];
				i++;
			}
			
			int z = i, e = 0;
			while(z < array.length)
			{
				array[e++] = array[z++];
			}
			
			z = array.length - 1;
			while(--i >= 0)
			{
				array[z--] = buffer[i]; 
			}
			print(array);
		}
		
	}
	
	public int calNewLocation(final int size, final int n, int i)
	{
		return (i - n + size) % size;
	}
	
	public void solve(char[] array, int n)
	{
		char currentChar = array[n];
		int  currentLocation = n;
		
		char newChar;
		int  newLocation;
		int startPoint = n;
		
		for (int i=0; i<array.length; i++)
		{
			newLocation = calNewLocation(array.length, n, currentLocation);
			System.out.println(currentLocation + " -> " + newLocation);
			newChar = array[newLocation];
			array[newLocation] = currentChar;
			currentLocation = newLocation;
			currentChar = newChar;
			if (i > 0 && currentLocation == startPoint && i+1<array.length)
			{
				currentLocation = ++startPoint;
				currentChar = array[currentLocation];
			}
		}
		
		print(array);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String in = "123456";
		new P2Rostate().solve(in.toCharArray(), 3);
	}

}
