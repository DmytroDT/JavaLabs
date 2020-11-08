import java.util.Scanner;

public class main {

	/**
	 * 
	 * @param any integer is permitted.They're just random numbers that will be
	 *            displayed in console.
	 * @param
	 * @param
	 */
	public static void TakeArguments(int a1, int a2, int a3) {
		System.out.println("Recieved variables are:" + a1 + ", " + a2 + ", " + a3);
	}

	public static void main(String[] args) {
		int Upb, Lwb, Fib;
		Scanner Sc = new Scanner(System.in);

		TestClass TC = new TestClass();

		System.out.println("Variable int:" + TC.intVar);
		System.out.println("Variable char:" + TC.charVar);

		TakeArguments(Sc.nextInt(), Sc.nextInt(), Sc.nextInt());

		System.out.println("Input lower border of numerical series:");
		Lwb = Sc.nextInt();
		System.out.println("Input upper border of numerical series:");
		Upb = Sc.nextInt();
		System.out.println("Input Fibonacchi border of numerical series:");
		Fib = Sc.nextInt();

		FibonacchiNumberline Fb = new FibonacchiNumberline(Lwb, Upb, Fib);
		Fb.DisplayNumbers();

	}

}
