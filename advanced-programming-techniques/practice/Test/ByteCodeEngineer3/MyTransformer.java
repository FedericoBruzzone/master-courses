import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.io.*;
import javassist.ClassPool;
import javassist.*;

public class MyTransformer implements ClassFileTransformer {
    private ClassPool cp = ClassPool.getDefault();

    @Override
    public byte[] transform(ClassLoader loader, 
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, 
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("MyTransformer::transform: " + className);
        try {
            if (className.equals("A")) {
                InputStream is = new ByteArrayInputStream(classfileBuffer);
                CtClass cls_a = cp.makeClass(is);
                CtMethod cls_m = cls_a.getDeclaredMethod("a");
                cls_m.insertBefore("System.out.println(\"MyLine\");");
                return cls_a.toBytecode();
            }      
            if (className.equals("java.lang.StringBuilder")) {
                InputStream is = new ByteArrayInputStream(classfileBuffer);
            } 
        } catch (Exception e) {e.printStackTrace();}                    
        return classfileBuffer;
    }
}