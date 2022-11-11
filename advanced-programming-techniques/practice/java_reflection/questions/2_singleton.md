```Java
package class_loader;

import java.lang.reflect.Field;
import java.io.*;

class SimpleClassLoader extends ClassLoader {
    String[] directories;

    public SimpleClassLoader(String path) {
        directories = path.split(";");
    }

    public SimpleClassLoader(String path, ClassLoader parent) {
        super(parent);
        directories = path.split(";");
    }

    public synchronized Class<?> findClass(String name) throws ClassNotFoundException {
        for (int i = 0; i < directories.length; i++) {
            byte[] buf = getClassData(directories[i], name);
            if (buf != null)
                return defineClass(name, buf, 0, buf.length);
        }
        throw new ClassNotFoundException();
    }

    protected byte[] getClassData(String directory, String fileName) {
        String classFile = directory + "/" + fileName.replace('.', '/') + ".class";
        int classSize = (int) (new File(classFile)).length();
        byte[] buf = new byte[classSize];
        try {
            FileInputStream filein = new FileInputStream(classFile);
            classSize = filein.read(buf);
            filein.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return buf;
    }
}

class Singleton {
    static private boolean runOnce = false;

    public Singleton() {
        if (runOnce)
            throw new IllegalStateException("[ERROR] re-instantiation of «Singleton»!!!");
        runOnce = true;
    }
}

class SingletonViolationTest {
    public static void main(String[] args) throws Exception {
        String s = new String();
        System.out.println(s.getClass().getClassLoader());
        SimpleClassLoader CL1 = new SimpleClassLoader("testclasses");
        Class<?> c1 = CL1.loadClass("class_loader.Singleton");
        System.out.println("Loaded class «Singleton» via the «CL1» class loader");
        Field flag = c1.getDeclaredField("runOnce");
        flag.setAccessible(true);
        System.out.println("Let's instatiate «Singleton@CL1»\n### runOnce :- "+flag.get(null));

        Object x = c1.getDeclaredConstructor().newInstance();
            System.out.println("### runOnce :- "+flag.get(null));

        try {
            System.out.println("Let's re-instantiate «Singleton@CL1»\n### runOnce :- "+flag.get(null));
            Object y = c1.getDeclaredConstructor().newInstance();
            throw new RuntimeException("Test Fails!!!");
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        }

        SimpleClassLoader CL2 = new SimpleClassLoader("testclasses");
        System.out.println("Loaded class «Singleton» via the «CL2» class loader");
        Class<?> c2 = CL2.loadClass("class_loader.Singleton");

        System.out.println(c1.getClassLoader().toString() + "\n" +
        c2.getClassLoader().toString());
        System.out.println("Same class loader: " + (c1.getClassLoader() ==
        c2.getClassLoader())); // What??? True??? Same package???
        System.out.println("Same class loader?: "+ CL1.equals(CL2));
        System.out.println("Same class loader2? :"+c1.getClassLoader().equals(c2.getClassLoader()));
        System.out.println("NONE'POSSIBILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+s.getClass().getClassLoader().equals(c2.getClassLoader()));

        Field flag2 = c2.getDeclaredField("runOnce");
        flag2.setAccessible(true);
        System.out.println("Let's instatiate «Singleton@CL2»\n### runOnce :- "+flag2.get(null));
        //Object z = c2.getDeclaredConstructor().newInstance();
        System.out.println("### runOnce :- "+flag.get(null));
    }
}
```

NON FUNZIONA
