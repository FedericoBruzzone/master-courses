import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.tools.reflect.Loader;

public class RunMyMetaobject {
	public static void main(String[] args) {
		try {
			Loader loader = new Loader();
			loader.makeReflective("A", "MyMetaobject", "javassist.tools.reflect.ClassMetaobject");
			loader.makeReflective("B", "MyMetaobject", "javassist.tools.reflect.ClassMetaobject");
			loader.run("Main", args);
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
