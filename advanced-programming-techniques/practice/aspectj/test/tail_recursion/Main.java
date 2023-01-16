public class Main {

  public int factorial(int n, int tail) throws TailException {
    if (n == 1) {
      return tail; 
    } else {
      return factorial(n-1, tail*n); 
    }
  }

  public int factorial(int a) throws TailException {
    return factorial(a, 1);
  }

  public static void main(String[] args) throws TailException {
    Main m = new Main();
    int res = m.factorial(10);
    System.out.println(res);
  }
}
