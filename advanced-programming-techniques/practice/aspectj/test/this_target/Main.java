public class Main {

 public static void main(String[] args) {
 	A a = new A(1000);
	a.test(123456);
  //System.out.println(a);
 
  System.out.println("===== B =====");
	B b = new B(2000);
	b.b_test();

  System.out.println("===== A static =====");
	a.a_static();
  
	System.out.println("===== B with A =====");
  B b1 = new B(3000, a);
	b1.b_test_with_a();
 }

}
