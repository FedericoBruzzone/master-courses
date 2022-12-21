public aspect DisplayUpdating {
  pointcut move(Line l):
		target(l) && (call(void Line.setP1(Point)) || call(void Line.setP2(Point)));
  
	after(Line line) returning: move(line) { System.out.println("test"); } 
}
