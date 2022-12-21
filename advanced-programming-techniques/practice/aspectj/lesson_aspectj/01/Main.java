public class Main {
  public static void main(String[] args) {
  	System.out.println("Main::main => start");
		Point p = new Point();
		p.setX(10);
		p.setY(10); 
    Line l = new Line();
		l.setP1(p);
		l.setP2(p);
		System.out.println(l.getP1());
	  System.out.println("Main::main => end");
	}
}
