import java.util.ArrayList;

class Test {}

public class MainTestClassLoader {
    // javac *.java
    // java -Djava.system.class.loader=MyClassLoader MainTestClassLoader
    // java MainTestClassLoader
    
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        Test t = new Test();
        System.out.println(al.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader());
    }
}
