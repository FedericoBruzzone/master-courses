import javassist.*;

class MyTranslator implements Translator {
    @Override
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        System.out.println("------------------------------ MyTranslator::start ------------------------------");
    }

    @Override
    public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
        System.out.println("------------------------------ MyTranslator::onLoad ------------------------------");
        System.out.println(classname);
    }
}

public class MyLoader {
    public static void main(String[] args) throws Throwable {
        System.out.println("------------------------------ MyLoader ------------------------------");
        ClassPool classPool = ClassPool.getDefault();
        Loader loader = new Loader(classPool);
        loader.addTranslator(classPool, new MyTranslator());
        loader.run("Main", args);
    }
}
