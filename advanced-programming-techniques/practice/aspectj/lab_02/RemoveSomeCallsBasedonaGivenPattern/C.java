public class C {

  A c_a = null;
	B c_b = null;

  public C() {
		this.c_a = new A();
		//this.c_b = new B();
	}

  public void c() {
    System.out.println("C::c");
		this.c_a.a(); 
		//this.c_b.b();
	}

}
