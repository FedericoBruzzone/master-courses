public class Point implements FigureElement {
  private int x = 0, y = 0;
  public int getX() { return x; }
  public int getY() { return y; }
  public void setX(int x) { this.x = x; }
  public void setY(int y) { this.y = y; }
  public void moveBy(int dx, int dy) { x += dx; y += dy; }
}
