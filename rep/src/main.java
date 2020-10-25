import java.util.Scanner;





public class main 
{
	/**
	 * 
	 * @param any integer is permitted.They're just random numbers that will be displayed in console. 
	 * @param 
	 * @param
	 */
	public  static void TakeArguments(int a1,int a2, int a3)
	{
		System.out.println("Recieved variables are:"+a1+", "+a2+", "+a3);
	}
	
	public static void main(String[] args) {
	
	
	TestClass TC = new TestClass(); 	
	
	System.out.println("Variable int:"+TC.intVar);
	System.out.println("Variable char:"+TC.charVar);
	
	Scanner Sc = new Scanner(System.in);
	
	TakeArguments(Sc.nextInt(), Sc.nextInt(), Sc.nextInt());

	FibonacchiNumberline Fb = new FibonacchiNumberline();
	Fb.InputBoarders();
	Fb.displayNumbers();
	Fb.DisplaySum();
	Fb.FibonacchiSeries();
	Fb.DisplayNumberRatio();
	
	}

	
	
}

