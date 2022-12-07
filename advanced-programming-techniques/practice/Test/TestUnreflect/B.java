import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class B extends A implements Y {
    public Object myUnreflectSpecial() throws Exception, Throwable {
        return MethodHandles
                    .lookup()
                    .unreflectSpecial(
                        Class.forName("A").getDeclaredMethod("y"), 
                        getClass())
                    .bindTo(this)
                    .invokeWithArguments();
    }

    @Override public void y() {
        System.out.println("!!!!!!");
    } 

    public static void main(String[] args) throws Exception, Throwable {
        B b = new B();
        b.myUnreflectSpecial();
    }
}
