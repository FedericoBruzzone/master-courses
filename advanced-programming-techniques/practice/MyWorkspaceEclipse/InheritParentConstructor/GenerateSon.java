import java.io.IOException;
import javassist.CannotCompileException;
import javassist.NotFoundException;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.Modifier;

// javac -cp /usr/share/java/javassist.jar *.java
// javap -c Parent
// java -cp .:/usr/share/java/javassist.jar GenerateSon
// javap -c Parent
// javap -c generate/son/Son
// java -cp .:/usr/share/java/javassist.jar Test

public class GenerateSon {
	public static void main(String[] args)  {
	    try {
	    	ClassPool pool = ClassPool.getDefault();
		    CtClass parent = pool.get("Parent");
		    parent.defrost();
		    parent.setModifiers(Modifier.clear(parent.getModifiers(), Modifier.FINAL));
		    parent.writeFile();
		    CtClass son = pool.makeClass("Son", parent);
		    son.writeFile();
	    } catch (IOException e) { e.printStackTrace(); } 
	      catch (NotFoundException e) { e.printStackTrace(); } 
	      catch (CannotCompileException e) { e.printStackTrace(); }
	}
}
