public interface Visible {
    public final int XMIN = -1080;
    public final int XMAX = 1080;
    public final int YMIN = -1920;
    public final int YMAX = 1920;

    default boolean isvisiblex(int x) { 
        return (x>= XMIN && x <= XMAX) ; 
    }

    default boolean isvisibley(int y) { 
        return (y>= YMIN && y <= YMAX) ; 
    }
}
