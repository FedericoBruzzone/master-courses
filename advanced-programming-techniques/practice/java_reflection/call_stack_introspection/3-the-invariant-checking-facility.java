package call_stack_introspection;

interface Visible {
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

interface InvariantSupporter { 
    boolean invariant(); 
}

// There is a loop when the invariant() invokes a method under invariant check.
// class InvariantChecker {
//     public static void checkInvariant(InvariantSupporter object) {
//         if(!object.invariant())
//             throw new IllegalStateException("invariant failure");
//     }
// }

class InvariantChecker {
    public static void checkInvariant(InvariantSupporter object) {
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        for (int i = 1 ; i<stackTraceElements.length; i++)
            if (stackTraceElements[i].getClassName().equals("InvariantChecker") &&
                stackTraceElements[i].getMethodName().equals("checkInvariant") ) 
            return;
        if (!object.invariant())
            throw new IllegalStateException("invariant failure");
    }
}

class VisiblePoint implements Visible, InvariantSupporter {
    private int x, y;

    public VisiblePoint(int x, int y) {
        this.x = x; this.y=y;
        assert isvisiblex(this.x) && isvisibley(this.y): 
            "x or y coordinates outside the display margins";
    }
    
    public int getX() { 
        //InvariantChecker.checkInvariant(this);
        int result =  this.x; 
        //InvariantChecker.checkInvariant(this);
        return result;
    }

    public int getY() { 
        //InvariantChecker.checkInvariant(this);
        int result =  this.y; 
        //InvariantChecker.checkInvariant(this);
        return result;
    }

    public void setX(int x) { 
        InvariantChecker.checkInvariant(this); 
        this.x=x; 
        InvariantChecker.checkInvariant(this); 
    }

    public void setY(int y) { 
        InvariantChecker.checkInvariant(this); 
        this.y=y; 
        InvariantChecker.checkInvariant(this); 
    }

    public boolean invariant() {
        return isvisiblex(getX()) && isvisibley(getY());
    }
}

class MainInvariantCheker {
    public static void main(String[] args) {
        VisiblePoint p = new VisiblePoint(-7, 25);
        System.out.println("Point p is: ("+p.getX()+", "+p.getY()+")");
        p.setX(-20);
        System.out.println("New Point p is: ("+p.getX()+", "+p.getY()+")");
        p.setX(-2000);
        System.out.println("New Point p is: ("+p.getX()+", "+p.getY()+")");
    }
}