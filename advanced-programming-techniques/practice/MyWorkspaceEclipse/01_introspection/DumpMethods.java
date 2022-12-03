// javac DumpMethods.java
// java DumpMethods java.lang.String
 
import java.lang.reflect.Method;

public class DumpMethods {

	public static void main(String[] args) {
		try {
			String s_cls = args[0];
			Class<?> cls = Class.forName(s_cls);
			// Class.getDeclaredMethods():
			//     Returns an array containing Method objects reflecting 
			//     all the declared methods of the class or interface represented 
			//     by this Class object, including public, protected, 
			//     default (package) access, and private methods, but excluding 
			//     inherited methods.
			// Class.getMethods():
			//     Returns an array containing Method objects reflecting all the public 
			//     methods of the class or interface represented by this Class object, 
			//     including those declared by the class or interface and those inherited 
			//     from superclasses and superinterfaces.
			for (Method m : cls.getMethods()) {
				System.out.println(m);
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

}
