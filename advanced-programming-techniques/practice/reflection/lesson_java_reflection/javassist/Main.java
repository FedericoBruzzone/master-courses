import javassist.tools.reflect.Loader;

// javac -cp /usr/share/java/javassist.jar *.java
// USEFUL FOR CASTING: java -cp .:/usr/share/java/javassist.jar javassist.tools.reflect.Loader Main "Gladstone"
// java -cp .:/usr/share/java/javassist.jar Main "Gladstone"

public class Main {
    public static void main(String[] args) throws Throwable {
        //Loader cl = (Loader) Main.class.getClassLoader();
        Loader cl = new Loader();
        cl.makeReflective("Person", "VerboseMetaobj", "javassist.tools.reflect.ClassMetaobject");
        cl.run("Person", args);
    }
}