import javassist.*;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar Patch
// java MyClass

public class Patch {
	public static void main(String[] args) throws Exception {
		ClassPool classPool = ClassPool.getDefault();
		CtClass sb = classPool.get("A");
		sb.defrost();
		CtMethod m = sb.getDeclaredMethod("a", null);
		
		m.insertBefore("System.out.println(\"MyLine\");");
		
		sb.writeFile();
	}
}
