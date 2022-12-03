// javac -cp /usr/share/java/javassist.jar DumpMethods.java
// java -cp .:/usr/share/java/javassist.jar DumpMethods java.lang.String

import javassist.*;

public class DumpMethods {

	public static void main(String[] args) {
		try {
			ClassPool cp = ClassPool.getDefault();
			String s_cls = args[0];
			CtClass cls = cp.get(s_cls);
			// CtClass.getDeclaredMethods():
			//     Gets all methods declared in the class.
			// CtClass.getMethods():
			//     Returns an array containing CtMethod objects representing 
			//     all the non-private methods of the class.
			for (CtMethod m : cls.getMethods()) {
				System.out.println(m.getLongName());
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

}
