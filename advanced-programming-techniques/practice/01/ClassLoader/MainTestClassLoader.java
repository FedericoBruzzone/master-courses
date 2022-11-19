public class MainTestClassLoader {

    public static void main(String[] args) throws Exception {
        MyClassLoader CL1 = new MyClassLoader("testclasses");
        Class<?> c1 = CL1.loadClass("java.lang.String");
    }

}
