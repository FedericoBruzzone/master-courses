public class A {
 
 int n;

 public A(int n) {
	this.n = n;
 } 

 public void test(int n2) {
  System.out.println("A::test " + n + " " + n2);
 }

 public static void a_static() {
  System.out.println("A::a_static");
 }

 public String toString () {
  return "A: " + this.n;
 }

}

class B {
 
 int n;
 A a;

 public B(int n) {
  this.n = n;
  this.a = null;
 }	

 public B(int n, A a) {
  this.n = n;
	this.a = a;
 }	

 public void b_test() {
  A.a_static();
 } 	
 
 public void b_test_with_a() {
  a.test(0);
 }
 
 public String toString() {
  return "B: " + this.n + " " + a; 
 }
 
}
