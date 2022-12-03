public class MainInvariantChecker {
    public static void main(String[] args) {
        VisiblePoint p = new VisiblePoint(-7, 25);
        System.out.println("Point p is: (" + p.getX() + ", " + p.getY() + ")");
        p.setX(-20);
        System.out.println("New Point is: (" + p.getX() + ", " + p.getY() + ")");
        p.setX(-2000);
        System.out.println("New Point is: (" + p.getX() + ", " + p.getY() + ")");
    }
}