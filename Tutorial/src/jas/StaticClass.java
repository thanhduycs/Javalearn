package jas;

public class StaticClass {
	
	public void run()
	{
		OuterClass outerClass = new OuterClass();
		OuterClass.InnerClass innerClass = null;
		
		innerClass = outerClass.new InnerClass();
		
		System.out.println(innerClass.inner); //I'm inner
		
		OuterClass.InnerStaticClass innerStaticClass = null;
		innerStaticClass = new OuterClass.InnerStaticClass();
		
		System.out.println(innerStaticClass.innerStatic); //I'm innerStatic
	}
	
	public static void main(String[] args) {
		new StaticClass().run();
	}
}

