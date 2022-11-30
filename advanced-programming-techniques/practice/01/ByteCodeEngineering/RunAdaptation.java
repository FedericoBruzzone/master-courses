import javassist.*;

public class RunAdaptation {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, Throwable {
        Adapter adapter = new Adapter();
        ClassPool classPool = ClassPool.getDefault();
        Loader loader = new Loader(classPool);
        loader.addTranslator(classPool, adapter);
        loader.run("TestApp", args);
    }
}
