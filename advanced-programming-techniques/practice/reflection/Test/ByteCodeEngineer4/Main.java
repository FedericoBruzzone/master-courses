// javac -cp /usr/share/java/javassist.jar *.java
// java -Djava.system.class.loader=MyClassLoader -cp .:/usr/share/java/javassist.jar Main

public class Main {
    public static void main(String[] args) {
        System.out.println("----- Main::main -----");
        A a = new A();
        a.a();
    }
}
