package dynamic_proxy;

import java.lang.reflect.*;

interface IPoint2 {
    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    public boolean invariant();
}

class Point2 implements IPoint2 {
    private int x, y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean invariant() {
        return x>=0 && y>=0;
    }
}

class InvariantHandler implements InvocationHandler {
    private Object target;
    private Method invariant;

    public InvariantHandler(Object target) {
        this.target = target;
        try {
            invariant = target.getClass().getMethod("invariant", new Class<?>[] {});
            if (!invariant.getReturnType().equals(boolean.class)) 
                invariant = null;
        } catch (NoSuchMethodException ex) {
            invariant = null;
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.invokeInvariant(method);
        Object retvalue = method.invoke(this.target, args);
        this.invokeInvariant(method);
        return retvalue;
    }

    private void invokeInvariant(Method method) {
        if ((this.invariant == null) || (method.equals(this.invariant))) 
            return;
        try {
            Boolean passed = (Boolean) invariant.invoke(target, new Object[] {});
            if (!passed.booleanValue())
                throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("Failed invariant check!!!");
        }
    }

    public String toString() {
        return "ih :- " + this.target;
    }
}

class Main2 {
    public static void main(String[] args) {
        InvariantHandler ih = new InvariantHandler(new Point2(0, 7));

        IPoint2 invariantCheckedPoint = (IPoint2) Proxy.newProxyInstance(
                Point2.class.getClassLoader(),
                new Class[] { IPoint2.class },
                ih);

        TraceHandler th = new TraceHandler(invariantCheckedPoint);

        IPoint2 tracedInvariantCheckedPoint = (IPoint2) Proxy.newProxyInstance(
                Point2.class.getClassLoader(),
                new Class[] { IPoint2.class }, 
                th);

        System.out.println(tracedInvariantCheckedPoint.getX());
        tracedInvariantCheckedPoint.setX(25);
        tracedInvariantCheckedPoint.setY(-7);
    }
}