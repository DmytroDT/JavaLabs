import java.util.ArrayList;

import javax.naming.directory.InvalidAttributeIdentifierException;
/**
 * 
 * @author dmytro
 * A class that holds numerous variables with properties of numerical series. 
 * Main way to work with it is through it's methods. 
 */

public class FibonacchiNumberline 
{
	private int lowerBoarder,upperBoarder, oddSum,evenSum,Fibborder,oddCount=1,evenCount=1;
	private long var1,var2,intermeiateResult; 
	private ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> evenNumbers = new ArrayList<Integer>();
	private ArrayList<Long> fibNumbers = new ArrayList<Long>();
	
	
	public FibonacchiNumberline(int lowerBoarder, int upperBoarder,int Fibonborder)
	{
		this.lowerBoarder = lowerBoarder;
		this.upperBoarder = upperBoarder;
		this.Fibborder=Fibonborder;
		
		SelectNumbers();
		NumbersSum();
		FibonacchiSeries();
	}
	
	
	
	
	private void SelectNumbers()
	{
		
		for(int i= lowerBoarder; i <= upperBoarder; i++)
		{
			if(i%2==0)
			{
				evenNumbers.add(i);

			}
			else 
			{
				oddNumbers.add(i);
			}
		}

	}
	
	
	private	void NumbersSum()
	{
	
		for(int i= lowerBoarder; i < upperBoarder; i++)
		{
			if(i%2==0)
			{
				evenSum+=i;
			}
			else 
			{
				oddSum+=i;
			}
		
		}
	}
	
	private void FibonacchiSeries()
	{
		
		var1=oddNumbers.get(oddNumbers.size()-1);
		var2=evenNumbers.get(evenNumbers.size()-1);
		fibNumbers.add(var1);
		fibNumbers.add(var2);
		
		
		for (int i=0; i< Fibborder;i++)
		{
			intermeiateResult=var1+var2;
			var1=var2;
			var2=intermeiateResult;
		
			fibNumbers.add(intermeiateResult);
			
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
	
	
	
	public void DisplayNumbers()
	{
			System.out.println("Even numbers:");
			for (int i=0;i<evenNumbers.size();i++)
			{
				System.out.println(evenNumbers.get(i));
			}
			System.out.println("Odd numbers:");
			for (int i=oddNumbers.size()-1;i>=0;i--)
			{
				System.out.println(oddNumbers.get(i));
			}
			
			System.out.println("Odd numbers sum :"+oddSum+" even numbers sum:"+evenSum);
			System.out.println("Fibonacci series:");
			for(int i=0;i<fibNumbers.size();i++)
			{
				System.out.println(fibNumbers.get(i));
			}

			System.out.println("Even number percentage is:"+(evenCount*100/fibNumbers.size())+"%.");
			System.out.println("Odd number percentage is:"+(oddCount*100/fibNumbers.size())+"%.");
		
		
	}
			
	
	
}
