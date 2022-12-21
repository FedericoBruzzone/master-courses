import javassist.*;

public class RunTranslator {
    public static void main(String[] args) throws NotFoundException, Throwable {
        Adapter adapter = new Adapter();
        ClassPool cp = ClassPool.getDefault();
        Loader loader = new Loader(cp);
        loader.addTranslator(cp, adapter);
        loader.run("Main", args);
    }
}
