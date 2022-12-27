// ajc -1.9 *aj *java
// aj TestPreConditions

public class TestPreConditions {

 public static void main(String[] args) {
  Point p = new Point();
  p.setY(10);

  try {
   p.setX(1025);
	} catch (Throwable t) {}
 }

}
