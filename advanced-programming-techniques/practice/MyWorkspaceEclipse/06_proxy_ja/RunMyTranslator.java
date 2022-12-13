import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;

//javac -cp /usr/share/java/javassist.jar *.java
//java -cp .:/usr/share/java/javassist.jar RunMyTranslator

public class RunMyTranslator {
	public static void main(String[] args) {
		try {
			MyTranslator myTranslator = new MyTranslator();
			ClassPool p = ClassPool.getDefault();
			Loader loader = new Loader(p);
			loader.addTranslator(p, myTranslator);
			loader.run("Main", args);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
