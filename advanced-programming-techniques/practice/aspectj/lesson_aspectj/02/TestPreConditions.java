// ajc -1.9 *aj *java
// aj TestPreConditions

public class TestPreConditions {

  public static void main(String[] args) throws Throwable {
    FigureFactory ff = new FigureFactory();
    Point ff_p = ff.makePoint(0, 0);
    System.out.println("ff_p has been created");

    ff_p.setX(0);
    ff_p.setY(0);

    // Point p = new Point(0,0);
    // System.out.println("p has been created");

    // p.setY(10);
    // try{
    // p.setX(1024);
    //// test(p);
    // } catch(Throwable t) {}
    // throw new Throwable();

    Line l1 = new Line(ff_p, ff_p);
    Line l2 = new Line(ff_p, ff_p);

    l1.p1 = l2.p1;

    throw new Exception();
  }

  // public static void test(Point p) {
  // p.setX(1025);
  // }

}
