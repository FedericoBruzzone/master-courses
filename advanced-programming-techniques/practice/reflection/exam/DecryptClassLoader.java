import java.io.FileInputStream;
import java.io.IOException;

public class DecryptClassLoader extends ClassLoader {
    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }
    
    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {
        if (className.startsWith("java")) {
            System.out.println("Loading JAVA class: " + className);
            return super.loadClass(className);
        }

        System.out.println("Loading USER class: " + className);
        return findClass(className);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String filename = className.replace(".", "/") + ".class";

            FileInputStream fileInputStream = new FileInputStream(filename);
            byte[] encryptedBytecode = fileInputStream.readAllBytes();
            fileInputStream.close();

            if (encryptedBytecode != null) {
                byte[] bytecode = new Decrypt().decrypt(encryptedBytecode);
                return defineClass(className, bytecode, 0, bytecode.length);
            }

            throw new IOException();
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
