class Main {
 
 public static void main(String[] args) {
	Line l = new Line();
	Point p = new Point();
	
	p.setX(10);
	p.setY(1024);
	
  p.setX("string");

	l.setP1();
	l.setP1(p);
	//l.setP2(p);
  //l.setP1(p, "p");
	//l.setP1nP2(p, p);
   
	System.out.println("Main::main :- " + p.getX());
 }

}
