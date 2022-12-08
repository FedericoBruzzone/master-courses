import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar PatchStringBuilder
// jar -f StringBuilder.jar -c java/lang/StringBuilder.class
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar Main

// javac -cp /usr/share/java/javassist.jar PatchStringBuilder.java
// java -cp .:/usr/share/java/javassist.jar PatchStringBuilder
// jar -f StringBuilder.jar -c java/lang/StringBuilder.class
// javac Main.java
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar Main

public class Main {
	public static void main(String[] args) {
		System.out.println("---- Main::main -----");
		try {
			Class<?> cls = Class.forName("FakeStringBuilder");
			StringBuilder sb = (StringBuilder) cls.getDeclaredConstructor().newInstance();
			System.out.println();
			System.out.println("----- Append -----");
			sb.append(new char[] { '1', '2' });
			System.out.println("After Append: " + sb);
			System.out.println();
			System.out.println("----- Insert -----");
			sb.insert(2, "34");
			System.out.println("After Insert: " + sb);
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		  catch (InstantiationException e) {e.printStackTrace();} 
		  catch (IllegalAccessException e) {e.printStackTrace();} 
		  catch (IllegalArgumentException e) {e.printStackTrace();} 
		  catch (InvocationTargetException e) {e.printStackTrace();} 
		  catch (NoSuchMethodException e) {e.printStackTrace();} 
		  catch (SecurityException e) {e.printStackTrace();}
	}
}
