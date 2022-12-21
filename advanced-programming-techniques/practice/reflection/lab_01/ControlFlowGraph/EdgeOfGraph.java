import java.lang.reflect.Method;

public class EdgeOfGraph {
    Class<?> from;
    Method m;
    Class<?> to;

    public EdgeOfGraph(Class<?> from, Method m, Class<?> to) {
        this.from = from;
        this.m = m;
        this.to = to;
    }

    public String toString() {
        return from.getName() + " ---(" + m.getName() + ")--> " + to.getName();
    }
}
