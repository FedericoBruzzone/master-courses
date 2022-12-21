import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

import javassist.*;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("java")) {
            return super.loadClass(name);
        } else {
            System.out.println(name);
            return findClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream file = new FileInputStream(name + ".class");
            byte[] classfileBuffer = file.readAllBytes();
            if (name.equals("Main")) {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod ctMethod = ctClass.getDeclaredMethod("main", new CtClass[] { classPool.get("java.lang.String[]") });
                ctMethod.insertAfter("System.out.println(\"CLASS LOADER\");");
                classfileBuffer = ctClass.toBytecode();
            }
            return defineClass(name, classfileBuffer, 0, classfileBuffer.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

}
