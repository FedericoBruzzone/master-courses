import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

class MyClassLoader extends ClassLoader {
    String[] directory;

    public MyClassLoader(String path) {
        directory = path.split(";");
    }

    public MyClassLoader(String path, ClassLoader parent) {
        super(parent);
        directory = path.split(";");
    }

    @Override 
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("Loading Class: " + name);
        if (!name.startsWith("java")) return getClass(name);
        return super.loadClass(name);
    }

    private Class<?> getClass(String name) throws Exception {
        String file = name.replace('.', File.separatorChar) + ".class";
        try {
            byte[] b = 
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Class<?> findClass(String name) {
        return null;
    }

    protected byte[] getClassData(String fileName) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        int size = stream.available();
        byte[] buff = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
