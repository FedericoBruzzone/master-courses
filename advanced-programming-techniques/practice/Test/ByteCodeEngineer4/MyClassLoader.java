import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

//import javassist.*;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader() { super(); }
    public MyClassLoader(ClassLoader parent) { super(parent); }

    // @Override
    // public Class<?> loadClass(String name) throws ClassNotFoundException {
    //     try {
    //         System.out.println(name);
    //         // if (name.equals("A")) {
    //         //     InputStream is = new FileInputStream(name + ".class");
    //         //     //byte[] bytes = is.readAllBytes();
    //         //     ClassPool cp = ClassPool.getDefault();
    //         //     CtClass myCls = cp.makeClass(is);
    //         //     myCls.getDeclaredMethod("a").insertBefore("System.out.println(\"MyLine\");");
    //         //     return myCls.toClass();
    //         // }
    //     } catch (Exception e) { e.printStackTrace(); }
    //     return super.loadClass(name);
    // }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class '" + name + "'");
        if (!name.startsWith("java")) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println(name);
        try {
            String myPath = name + ".class";
            
            InputStream fis = new FileInputStream(myPath);
            byte[] b = fis.readAllBytes();
            fis.close();
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
}   
