// ajc -1.9 *aj *java
// aj TestPreConditions

public class TestPreConditions {

 public static void main(String[] args) /*throws Throwable*/ {
  Point p = new Point();
  //p.setY(10);
	try{
   p.setX(1025); 
	 //test(p);
	} catch(Throwable t) {}
	//throw new Throwable(); 
 }
 
 //public static void test(Point p) {
  //p.setX(1025);
 //}

}
