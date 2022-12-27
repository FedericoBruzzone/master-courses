import java.lang.Math.*;

public aspect PointBoundsEnforcement {
  
 public static int MAX_X = 1024;
 public static int MIN_X = 0;
 public static int MAX_Y = 1024;
 public static int MIN_Y = 0;

 pointcut onSetX(int newX):
  call(void Point.setX(int)) &&
	args(newX);
 
 void around(int newX): onSetX(newX) {
	System.out.println("around");
	proceed(clip(newX, MIN_X, MAX_X));
 }

 pointcut onSetY(int newY):
  call(void Point.setY(int)) &&
	args(newY);
 
 void around(int newY): onSetY(newY) {
  System.out.println("around");
	proceed(clip(newY, MIN_Y, MAX_Y));
 }

 private int clip(int val, int min, int max) {
  return Math.max(min, Math.min(max, val));
 }

} 
