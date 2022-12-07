// javac -cp /usr/share/java/javassist.jar *.java
// jar -m MANIFEST.MF -f Premain.jar -c Premain.class
// java -javaagent:Premain.jar -cp .:/usr/share/java/javassist.jar Main

public class Main {
    public static void main(String[] args) {
        System.out.println("----- Main::main -----");
        A a = new A();
        a.a();
    }
}
