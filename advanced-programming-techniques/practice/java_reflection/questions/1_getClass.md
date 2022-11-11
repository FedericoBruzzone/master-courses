```Java
package dynamic_proxy;

import java.lang.reflect.*;

interface IPoint {
    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);
}

class Point implements IPoint {
    private int x, y;

    public Point(int x, int y) {
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
}

class TraceHandler implements InvocationHandler {
    private Object baseObject;

    public TraceHandler(Object base) {
        this.baseObject = base;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        try {
            System.out.println("before " + m.getName());
            Object result = m.invoke(baseObject, args);
            System.out.println("after " + m.getName());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "th :- " + this.baseObject;
    }
}

class Main {
    public static void main(String[] args) {
        IPoint p1 = new Point(10, 20);
        IPoint th_p1 = (IPoint) Proxy.newProxyInstance(
            p1.getClass().getClassLoader(),
            p1.getClass().getInterfaces(),
            new TraceHandler(p1));

        System.out.println(th_p1.hashCode());
        System.out.println(th_p1.toString());
        System.out.println(th_p1.getClass());
    }
}
```

```Java
before hashCode
after hashCode
1229416514

before toString
after toString
dynamic_proxy.Point@49476842

class dynamic_proxy.$Proxy0
```

Ci chiededavamo perchè invoke del proxy viene invocato anche per i metodi non appartenenti all'interfaccia,
ma sopratutto al perchè th_p1.getClass() diversamente dagli altri non passa dall'invoke.

