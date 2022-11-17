import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.lang.reflect.*;

class TestingFields {
    private Double[] d;
    private Date dd;
    private int the_answer = 42;

    public TestingFields(int n, double val) {
        dd = new Date();
        d = new Double[n];
        for (int i = 0; i < n; i++)
            d[i] = i * val;
    }

    public void setAnswer(int a) {
        the_answer = a;
    }

    public String message() {
        return "The answer is " + the_answer;
    }
}

interface I {
    public void setAnswer(int a);
    public String message();
}

class MyTestingFields extends TestingFields implements I {

    public MyTestingFields(int n, double val) {
        super(n, val);
    }
}

// Logging
class MyProxy implements InvocationHandler {
    private Object object;

    public MyProxy(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Exception {
        try {
            //System.out.println(object.getClass().getMethod("message").invoke(object));
            System.out.println(object.toString());
            Object r = m.invoke(object, args);
            System.out.println(object.toString());
            //System.out.println(object.getClass().getMethod("message").invoke(object));
            Collections.sort((List<Integer>)object);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
}

class MainProxy {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // I tf = new MyTestingFields(7, 3.14); 
        // I i_MyTestingFields = (I) Proxy.newProxyInstance(
        //     tf.getClass().getClassLoader(), 
        //     tf.getClass().getInterfaces(), 
        //     new MyProxy(tf));
        
        // i_MyTestingFields.setAnswer(10);

        List<Integer> tf = new ArrayList<Integer>();
        List<Integer> i_MyTestingFields = (List<Integer>) Proxy.newProxyInstance(
            tf.getClass().getClassLoader(), 
            tf.getClass().getInterfaces(), 
            new MyProxy(tf));
        
        i_MyTestingFields.add(10);
        i_MyTestingFields.add(22);
        i_MyTestingFields.add(33);
        i_MyTestingFields.add(1);
        i_MyTestingFields.add(2);
        i_MyTestingFields.add(-1);
        
        for (var i : i_MyTestingFields) {
            System.out.println(i);
        }

    }
}
