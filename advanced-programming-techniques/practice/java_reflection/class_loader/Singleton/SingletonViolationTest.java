import java.lang.reflect.*;

public class SingletonViolationTest {
    public static void main(String[] args) throws Exception {
        SimpleClassLoader CL1 = new SimpleClassLoader("\"\";testclasses");
        Class<?> c1 = CL1.loadClass("Singleton");
        System.out.println("Loaded class «Singleton» via the «CL1» class loader");
        Field flag = c1.getDeclaredField("runOnce");
        flag.setAccessible(true);
        System.out.println("Let's instatiate «Singleton@CL1»\n### runOnce :- "+flag.get(null));

        Object x = c1.getDeclaredConstructor().newInstance();
        System.out.println("### runOnce :- "+flag.get(null));

        try {
            System.out.println("Let's re-instantiate «Singleton@CL1»\n### runOnce :- "+flag.get(null));
            Object y = c1.getDeclaredConstructor().newInstance();
            throw new RuntimeException("Test Fails!!!");
        } catch (Exception e) { System.out.println(e.getCause().getMessage()); }

        SimpleClassLoader CL2 = new SimpleClassLoader("\"\";testclasses");
        Class<?> c2 = CL2.loadClass("Singleton");
        System.out.println("Loaded class «Singleton» via the «CL1» class loader");
        Field flag2 = c2.getDeclaredField("runOnce");
        flag2.setAccessible(true);
        System.out.println("Let's instatiate «Singleton@CL1»\n### runOnce :- "+flag2.get(null));
        Object z = c2.getDeclaredConstructor().newInstance();
        System.out.println("### runOnce :- "+flag2.get(null));
        // SimpleClassLoader CL2 = new SimpleClassLoader("testclasses");
        // System.out.println("Loaded class «Singleton» via the «CL2» class loader");
        // Class<?> c2 = CL2.loadClass("class_loader.Singleton");

        // //System.out.println(c1.getClassLoader().toString() + "\n" + c2.getClassLoader().toString());
        // // System.out.println("Same class loader: " + (c1.getClassLoader() == 
        // // c2.getClassLoader())); // What??? True??? Same package???
        // // System.out.println("Same class loader?: "+ CL1.equals(CL2));
        // // System.out.println("Same class loader2? :"+c1.getClassLoader().equals(c2.getClassLoader()));
        // //System.out.println("NONE'POSSIBILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+s.getClass().getClassLoader().equals(c2.getClassLoader()));        
        
        // Field flag2 = c2.getDeclaredField("runOnce");
        // flag2.setAccessible(true);
        // System.out.println("Let's instatiate «Singleton@CL2»\n### runOnce :- "+flag2.get(null));
        // Object z = c2.getDeclaredConstructor().newInstance();
        // System.out.println("### runOnce :- "+flag.get(null));
    }
}