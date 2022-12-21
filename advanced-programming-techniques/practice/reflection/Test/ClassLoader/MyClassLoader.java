import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader parent) { 
        super(parent); 
    }

    @Override
    public Class<?> loadClass(String name) {
        try {
            if (name.equals("Test")) {
                return findClass(name);
            }
            return super.loadClass(name);
        } catch (ClassNotFoundException e) { e.printStackTrace(); return null; }
    }

    @Override
    public Class<?> findClass(String name) {
        try {
            if (name.equals("Test")) {
                byte[] testByte = getByteOf(name);
                return defineClass(name, testByte, 0, testByte.length);
            }
            return super.findClass(name);
        } catch (ClassNotFoundException e) { e.printStackTrace(); return null; }
    }

    protected byte[] getByteOf(String name) {
        try {
            String dir = name.replace(".", "/") + ".class";
            FileInputStream fis = new FileInputStream(dir);
            byte[] bytes = fis.readAllBytes();
            fis.close();
            return bytes;
        } catch (FileNotFoundException e) { e.printStackTrace(); } 
          catch (IOException e) { e.printStackTrace(); }
        return null;
    }
} 