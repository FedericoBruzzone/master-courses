public class VisiblePoint implements Visible, InvariantSupporter {
    private int x, y;

    public VisiblePoint(int x, int y) {
        this.x = x;
        this.y = y;
        assert isvisiblex(this.x) && isvisibley(this.y) : "x or y coordinates outside the display margins";
    }

    public boolean invariant() {
        return isvisiblex(getX()) && isvisibley(getY());
    }

    public int getX() {
        InvariantChecker.checkInvariant(this);
        int result = this.x;
        InvariantChecker.checkInvariant(this);
        return result;
    }

    public int getY() {
        InvariantChecker.checkInvariant(this);
        int result = this.y;
        InvariantChecker.checkInvariant(this);
        return result;
    }

    public void setX(int x) {
        InvariantChecker.checkInvariant(this);
        this.x = x;
        InvariantChecker.checkInvariant(this);
    }

    public void setY(int y) {
        InvariantChecker.checkInvariant(this);
        this.y = y;
        InvariantChecker.checkInvariant(this);
    }
}