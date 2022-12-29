class Point implements FigureElement {
 
 public /*private*/ int x, y; 
 
 public Point() {
  this.x = 0;
	this.y = 0;
 }

 public Point(int x, int y) {
  this.x = x;
	this.y = y;
 }

 public int getX() {
  return x;
 }

 public int getY() {
  return y;
 }

 public void setX(int x) {
  this.x = x;
 }
 
 public void setX(String x) {
  
 }

 public void setY(int y) {
  this.y = y;
 }

 public void moveBy(int dx, int dy) {
  x += dx;
  y += dy;
 }
  
 public String toString() {
  return "Point(" + this.x + ", " + this.y + ")";
 } 
 
}
