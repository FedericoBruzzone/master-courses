public class Main {

  public static void main(String[] args) {
    A a = new A();
    B b = new B();

//  System.out.println(MyAspect.aspectOf(b).toString()); // for pertarget & perthis
//	System.out.println(MyAspect.aspectOf(b).toString()); // for pertarget & perthis

	System.out.println(MyAspect.aspectOf(a));
    System.out.println(MyAspect.aspectOf(b));

    b.increment();
    b.increment();

    a.increment();
    a.increment();
    a.increment();
    a.increment();
    a.increment();


  }

}