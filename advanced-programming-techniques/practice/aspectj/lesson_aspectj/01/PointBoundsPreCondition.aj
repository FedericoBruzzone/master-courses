public aspect PointBoundsPreCondition {
 public static int MAX_X = 1024;
 public static int MIN_X = 0;
 public static int MAX_Y = 1024;
 public static int MIN_Y = 0;
 
 pointcut onSetX(int newX):
  call(void Point.setX(int)) && args(newX);

 pointcut onSetY(int newY):
  call(void Point.setY(int)) && args(newY);

 before(int newX): onSetX(newX) {
  theAssert(newX >= MIN_X); 
  theAssert(newX <= MAX_X);
 }

 before(int newY): onSetY(newY) {
  theAssert(newY >= MIN_Y); 
  theAssert(newY <= MAX_Y);
 }
 
 private void theAssert(boolean v) {
  if (!v)
		throw new RuntimeException();
 }
} 
