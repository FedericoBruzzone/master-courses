import java.io.*;

public class MyClassLoader extends ClassLoader {
    String[] directories;

    public MyClassLoader(String path) {
        directories = path.split(";");
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
        directories = new String[]{""};
    }

    public MyClassLoader(String path, ClassLoader parent) {
        super(parent); 
        directories = path.split(";");
    }

    @Override 
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.startsWith("java")) { 
            System.out.println("Loading class from MyClassLoader: " + "`" + name + "`"); 
            return findClass(name); 
        }
        // System.out.println("Loading class from DefaultClassLoader: " + "`" + name + "`"); 
        // return super.loadClass(name);
        throw new RuntimeException();
    }

    public synchronized Class<?> findClass(String name) throws ClassNotFoundException {
        for ( int i = 0; i < directories.length; i++ ) {
            byte[] buf = getClassData( directories[i], name ) ;
            if (buf != null) return defineClass(name,buf,0,buf.length);
        }
        throw new ClassNotFoundException();
    }

    private byte[] getClassData(String directory, String name) {
        String classFile;
        if (directory.equals("")) {
            classFile = name.replace('.', '/') + ".class";
        } else {
            classFile = directory + "/" + name.replace('.', '/') + ".class";
        }
        
        int classFileSize = (int)(new File(classFile)).length();
        byte[] bufferClass = new byte[classFileSize];
        try {
            FileInputStream fileInputStream = new FileInputStream(classFile);
            classFileSize = fileInputStream.read(bufferClass);
            fileInputStream.close();
        } catch (Exception e) { e.printStackTrace(); return null; }
        return bufferClass;
    }
}
