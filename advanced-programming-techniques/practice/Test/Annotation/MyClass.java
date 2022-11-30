import java.lang.reflect.InvocationTargetException;

@MyAnnotation("MyClass' annotation")
public class MyClass {

    public void myMethod() {
        System.out.println("MyMethod");
    }

    public static void main(String[] args) {
        try {
            Class<?> myClass = Class.forName("MyClass");
            Object oMyClass = myClass.getConstructor().newInstance();
            MyAnnotation myAnnotation = oMyClass.getClass().getDeclaredAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.value());
            System.out.println(myAnnotation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
