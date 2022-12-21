import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.*;

class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("MyTransformer::transform: " + className);
        return classfileBuffer;
    }
}

public class Premain {
    public static void premain(String args, Instrumentation i) {
        System.out.println("------------------------------ Premain::premain ------------------------------");
        i.addTransformer(new MyTransformer());
    }
}
