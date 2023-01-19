public class Dummy {

	public void m1(String s) {
    System.out.println(s);
	}

	public void m2(int i) {
    System.out.println(i);
	}

	public void m2(String s, int i) {
    System.out.println(s + " " + i);
	}

  public static void main(String[] args) {
  	Dummy d1 = new Dummy();
		Dummy d2 = new Dummy();

		d1.m1("A");
		d2.m1("B");
		d2.m1("C");
		
    d2.m2(1);
		d2.m2("A", 1);
    d1.m2(1);
		d1.m2("A", 1);
    d2.m2(1);
		d2.m2("A", 1);

	}

}
