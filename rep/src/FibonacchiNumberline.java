import java.util.Scanner;

/**
 * 
 * @author dmytro
 * A class that holds numerous variables with properties of numerical series. 
 * Main way to work with it is through it's methods. 
 */

public class FibonacchiNumberline 
{
	private int lowerBoarder,upperBoarder, oddSum,evenSum;
	private int border,oddCount=1,evenCount=1;
	private long var1,var2,intermeiateResult; 
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Void method that requires user to input upper & lower border of numerical series.
	 */
	void InputBoarders()
	{
		
		System.out.println("Input lower border of numerical series:");
		lowerBoarder = scan.nextInt();	
		System.out.println("Inpuit upper border of numerical series:");
		upperBoarder = scan.nextInt();
	}
	/**
	 * Method that displays series of integer numbers set in borders defined by InputBoarders method.
	 */
	void displayNumbers()
	{
		for(int i = upperBoarder; i>=lowerBoarder;i--)
		{
			if(i %2 != 0 )
			{
				System.out.print("Odd number="+i+" ");
			}
		}
		System.out.println("");
		
		for(int i= lowerBoarder; i <= upperBoarder; i++)
		{
			if(i%2==0)
			{
				System.out.print("Even number="+i+" ");

			}
		}

	}
	/**
	 * Method that displays a sum of even or odd numbers in series defined by border of InputBoarders method.
	 */
	void DisplaySum()
	{
		for(int i = upperBoarder; i>lowerBoarder;i--)
		{
			if(i %2 != 0 )
			{
				oddSum+=i;
			}
		}
		System.out.println("\nSum of all odd numbers is equal to:"+oddSum);
		
		for(int i= lowerBoarder; i < upperBoarder; i++)
		{
			if(i%2==0)
			{
				evenSum+=i;
			}
		}
		System.out.println("Sum of all even numbers is equal to:"+evenSum);
	}
	
	/**
	 * Method that creates Fibonacci Series out of upper border specified by InputBoarders method. 
	 */
	void FibonacchiSeries()
	{
		
		
	
		System.out.println("Input length of the series:");
		border = scan.nextInt();
		
		if(upperBoarder%2==0) 
		{
			var1=upperBoarder;
			var2=upperBoarder-1;
		}
		else
		{
			var1=upperBoarder;
			var2=upperBoarder-1;
		}
		System.out.println("Fibonacchi series number [0]= "+var1);
		System.out.println("Fibonacchi series number [1]= "+var2);
		
		for (int i=1; i< border;i++)
		{
			intermeiateResult=var1+var2;
			var1=var2;
			var2=intermeiateResult;
			System.out.println("Fibonacchi series number ["+(i+1)+"]= "+intermeiateResult);
			if(intermeiateResult%2==0) 
			{
				evenCount++;
			}
			else
			{
				oddCount++;
			}
		}
	}
	/**
	 * Method that displays % ratio of even & odd numbers in Fibonacchi series.
	 */
	void DisplayNumberRatio()
	{
		if(upperBoarder==0)
		{
			System.out.println("Upper limit is not set.");
		}
		else
		{
			System.out.println("Even number percentage is:"+(evenCount*100/border)+"%.");
			System.out.println("Odd number percentage is:"+(oddCount*100/border)+"%.");
		}
		
	}
			
	
	
}
