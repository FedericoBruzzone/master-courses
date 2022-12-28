import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{

    public MyClassLoader() {
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader(String name, ClassLoader parent) {
        super(name, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("java")) {
            return super.loadClass(name);
        } else {
            try {
                FileInputStream f  = new FileInputStream(name.replace('.', '/') + ".class");
                Decrypt d = new Decrypt();
                byte[] b = d.decrypt( f.readAllBytes());
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.loadClass(name);
    }
    
}
