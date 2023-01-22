class B {
  public int b() {
    System.out.println("b");
	  return 0;
	}
}

class A {
  
  B b = new B();

  public int test() {
    System.out.println("test");
		b.b();
		return 0;
	}

}
