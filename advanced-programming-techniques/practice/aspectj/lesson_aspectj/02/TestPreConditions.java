// ajc -1.9 *aj *java
// aj TestPreConditions

public class TestPreConditions {

 public static void main(String[] args) /*throws Throwable*/ {
  FigureFactory ff = new FigureFactory();
	Point ff_p = ff.makePoint(0, 0);
  System.out.println("ff_p has been created");

	Point p = new Point(0,0);
  System.out.println("p has been created"); 
	//p.setY(10);
	try{
   p.setX(1024); 
	 //test(p);
	} catch(Throwable t) {}
	//throw new Throwable(); 
 }
 
 //public static void test(Point p) {
  //p.setX(1025);
 //}

}
