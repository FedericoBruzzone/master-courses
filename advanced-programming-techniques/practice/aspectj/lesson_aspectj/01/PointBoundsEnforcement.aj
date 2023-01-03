import java.lang.Math.*;

public aspect PointBoundsEnforcement {
  
 public static int MAX_X = 1024;
 public static int MIN_X = 0;
 public static int MAX_Y = 1024;
 public static int MIN_Y = 0;

 pointcut onSetX(int newX):
  call(void Point.setX(int)) &&
	args(newX);
 
 pointcut onSetY(int newY):
  call(void Point.setY(int)) &&
	args(newY);
 
 void around(int newX): onSetX(newX) {
	System.out.println("before around");
	proceed(clip(newX, MIN_X, MAX_X));
 	System.out.println("after around");
 }
 
 void around(int newY): onSetY(newY) {
	proceed(clip(newY, MIN_Y, MAX_Y));
 }

 private int clip(int val, int min, int max) {
  return Math.max(min, Math.min(max, val));
 }
 
 pointcut onGet(Point x):
	target(x) &&
	call(int Point.getX());
 
 int around(Point x): onGet(x) {
	System.out.println("before onGet");
	int tmp = proceed(x);
	System.out.println("after onGet");
	return tmp;
 }
 
} 
