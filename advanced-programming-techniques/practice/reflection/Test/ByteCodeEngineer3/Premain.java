import java.lang.instrument.Instrumentation;
import javassist.*;

public class Premain {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("----- Premain::premain -----");
        MyTransformer myTransformer = new MyTransformer();
        instrumentation.addTransformer(myTransformer);
    }
}
