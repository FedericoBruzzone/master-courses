import java.lang.reflect.*;

class DumpMethods {
    public static void main(String[] args) throws ClassNotFoundException {
        for (var cls : args) {
            Class<?> c = Class.forName(cls);
            System.out.println(c);
            var m = c.getMethods();
            for (var j = 0; j < m.length; j++) {
                System.out.println(m[j]);
            }
        }
    }
}