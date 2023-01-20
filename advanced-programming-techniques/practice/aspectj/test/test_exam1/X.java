public class X implements I {

  @Override public void m1() {
    System.out.println("X::m1");
	}

	@Override public String m2() {
    return "X::m2"; 
	}

	@Override public int m3(int n) {
    return m3(n-1);
	}

  public void newM() {
    System.out.println("X::newM");
	}

}
