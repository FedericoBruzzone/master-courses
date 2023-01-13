public class TestFigures {

  public static void main(String[] args) {
    FigureElement[] fe = {
      new Point(5, 3), new Point(-1, 2), new Line(new Point(-5, 3), new Point(-1, -2))
    };

    if (10 > 0)
      try {
        System.out.println("jfdb");
      } catch (Exception e) {
      }

    for (FigureElement f : fe) {
      System.out.println(f);
      f.moveBy(3, -5);
    }

    for (FigureElement f : fe) System.out.println(f);
    Point p = new Point(0, 0);
    p.setX(10);
    p.setX(0);
  }
}
