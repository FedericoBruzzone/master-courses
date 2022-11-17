import java.io.*;

public class SimpleClassLoader extends ClassLoader {
   String[] directories;
   public SimpleClassLoader(String path) { directories = path.split( ";" ); }
   public SimpleClassLoader(String path, ClassLoader parent) {
      super(parent);
      directories = path.split(";");
   }

   public synchronized Class<?> findClass(String name) throws ClassNotFoundException {
     for ( int i = 0; i < directories.length; i++ ) {
        byte[] buf = getClassData( directories[i], name ) ;
        if (buf != null) return defineClass(name,buf,0,buf.length);
     }
     throw new ClassNotFoundException();
   }

   protected byte[] getClassData( String directory, String fileName ) {
     String classFile = directory + "/" + fileName.replace('.','/') + ".class";
     int classSize = (int)(new File(classFile)).length();
     byte[] buf = new byte[classSize];
     try {
        FileInputStream filein = new FileInputStream(classFile);
        classSize = filein.read(buf);
        filein.close();
     } catch(FileNotFoundException e) { return null; }
       catch(IOException e) { return null; }
     return buf;   
   }
}
