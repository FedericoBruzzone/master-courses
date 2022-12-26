public aspect PointBoundsPostCondition {
 
 pointcut afterSetX(Point p, int newX):
	target(p)                  &&
	call(void Point.setX(int)) &&
	args(newX);
 
 pointcut afterSetY(Point p, int newY):
	target(p)                  &&
	call(void Point.setY(int)) &&
	args(newY);

 after(Point p, int newX) returning: afterSetX(p, newX) {
  theAssert(p.getX() == newX); 
 }
 
 after(Point p, int newY) returning: afterSetY(p, newY) {
	System.out.println(p.getY() == newY);
	theAssert(p.getY() == newY); 
 }
	
 private void theAssert(boolean v) {
  if (!v)
	 throw new RuntimeException();
 }

}

