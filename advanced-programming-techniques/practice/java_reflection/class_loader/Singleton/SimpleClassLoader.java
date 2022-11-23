import java.io.*;

public class SimpleClassLoader extends ClassLoader {
 String[] directories;

 public SimpleClassLoader(String path) {
  directories = path.split(";");
 }

 public SimpleClassLoader(String path, ClassLoader parent) {
  super(parent);
  directories = path.split(";");
 }

 @Override
 public Class<?> loadClass(String name) throws ClassNotFoundException {
  if (!name.startsWith("java")) {
   System.out.println("Loading class from MyClassLoader: " + "`" + name + "`");
   return findClass(name);
  }
  System.out.println("Loading class from DefaultClassLoader: " + "`" + name + "`");
  return super.loadClass(name);
 }

 public synchronized Class<?> findClass(String name) throws ClassNotFoundException {
  for (int i = 0; i < directories.length; i++) {
   System.out.println(directories[i]);
   byte[] buf = getClassData(directories[i], name);
   if (buf != null)
    return defineClass(name, buf, 0, buf.length);
  }
  throw new ClassNotFoundException();
 }

 protected byte[] getClassData(String directory, String fileName) {
  String classFile;
  if (directory != "") {
   classFile = directory + "/" + fileName.replace('.', '/') + ".class";
  } else {
   classFile = fileName.replace('.', '/') + ".class";
  }
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
