//jar -cmvf META-INF/MANIFEST.MF /StringBuilder.jar java/lang/StringBuilder.class
import javassist.*;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar RunAdaptation
// jar cf StringBuilder.jar java/lang/StringBuilder.class
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar RunAdaptation

//javap -c java/lang/StringBuilder.class



public class RunAdaptation {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, Throwable {
        Adapter adapter = new Adapter();
        ClassPool classPool = ClassPool.getDefault();
        Loader loader = new Loader(classPool);

        System.out.println("RunAdaptation: START addTranslator method... ");
        loader.addTranslator(classPool, adapter);
        System.out.println("RunAdaptation: STOP addTranslator method ... ");
        System.out.println();

        System.out.println("RunAdaptation: START run method... ");
        loader.run("TestApp", args);
        System.out.println("RunAdaptation: STOP run method ... ");
        System.out.println();
    }
}
