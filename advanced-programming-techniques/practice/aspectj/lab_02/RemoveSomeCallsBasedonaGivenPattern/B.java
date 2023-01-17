public class B {
  
	A c_a = null;
	C c_c = null;

  public B() {
    this.c_a = new A();
		//this.c_c = new C();
	}

  public void b() {
    System.out.println("B::b");
    this.c_a.a(); 
		//this.c_c.c();
	}

}
