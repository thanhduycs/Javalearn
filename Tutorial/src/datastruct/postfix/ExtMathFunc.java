package datastruct.postfix;

public class ExtMathFunc {
   public static double factorial(double n)
   {
       if (n<=1)
           return 1.0;
       else
           return n * factorial(n-1);
   }
   public static double frac(double a)
   {
       return a - Math.floor(a);
   }
}

