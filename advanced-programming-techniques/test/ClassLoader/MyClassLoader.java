public class MyClassLoader extends ClassLoader {
    @Override 
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.startsWith("java")) { 
            System.out.println("Loading class from MyClassLoader: " + "`" + name + "`"); 
            return findClass(name); 
        }
        throw new RuntimeException();
    }
}
