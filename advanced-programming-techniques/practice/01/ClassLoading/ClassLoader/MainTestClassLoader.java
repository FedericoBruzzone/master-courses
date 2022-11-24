public class MainTestClassLoader {
    
    // javac TestClass.java MyClassLoader.java MainTestClassLoader.java
    // java -Djava.system.class.loader=MyClassLoader MainTestClassLoader
    // java MainTestClassLoader
    public static void main(String[] args) throws Exception {
        MyClassLoader CL1 = new MyClassLoader(""); // (ClassLoader)null
        Class<?> tc = CL1.loadClass("TestClass");
        println("Loaded class `TestClass` via the `CL1` class loader");
        Object testClass = tc.getDeclaredConstructor().newInstance();
        // println(testClass.getClass().getClassLoader());
        // println(Object.class.getClassLoader());
        System.out.println(Object.class.getClassLoader());
    }

    private static void println(Object string) {
        System.out.println(string);
    }

}
