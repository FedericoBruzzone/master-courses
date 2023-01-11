public class FigureFactory {
 
 public Line makeLine(Point p1, Point p2) { 
  System.out.println("FigureFactory::makeLine");
	return new Line(p1, p2); 
 }
 
 public Point makePoint(int x, int y) { 
  System.out.println("FigureFactory::makePoint");
  return new Point(x, y); 
 }

}
