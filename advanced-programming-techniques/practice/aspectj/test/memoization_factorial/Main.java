import java.util.*;

public class Main {

  public int factorial(int n) {
    if (n == 0) {
      return 1;
		}	
		else {
      return n * factorial(n-1);
		}
	}

  public int testNoRec(int n) {
    return n+1;
	}

  public void testVoid() {
    System.out.println("testVoid");
	}

	public static void main(String[] args) {
		Main main = new Main();
		A a = new A();
		int res =  main.factorial(10);
		System.out.println(res);
		int res2 =  main.factorial(11);
		System.out.println(res2);
    //int res3 = main.testNoRec(10);
		//System.out.println(res3);
    //int res4 = main.testNoRec(10);
		//System.out.println(res4);




		//main.testVoid();
		//a.test();
	}

} 
