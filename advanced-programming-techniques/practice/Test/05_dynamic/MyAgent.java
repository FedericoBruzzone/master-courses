import java.lang.instrument.*;

public class MyAgent{
    
    public static void premain(String className, Instrumentation inst){
        System.out.println("I'm a Premain! :D ");
        //System.out.println("I received the string: "+className);
        inst.addTransformer(new MyClassTransformer());
    }
}