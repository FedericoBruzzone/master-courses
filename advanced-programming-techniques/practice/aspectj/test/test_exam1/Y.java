public class Y implements I {

	X x = new X();

	@Override
	public void m1() {
		x.m1();
		x.newM();
		System.out.println("\tY::m1");
	}

	@Override
	public String m2() {
		System.out.println(x.m2());
		return "\tY::m2";
	}

	@Override
	public int m3(int n) {
		if (n == 0) {
			return n;
		}
		return n - m3(n - 1);
	}
}
