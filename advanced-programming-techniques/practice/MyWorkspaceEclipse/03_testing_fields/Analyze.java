// javac *.java
// java Analyze

import java.lang.reflect.*;

public class Analyze {
	public static void main(String[] args) {
		try {
			TestingFields tf = new TestingFields(7, 3.14);
			Field f = tf.getClass().getDeclaredField("s");
			f.setAccessible(true);
			f.set(tf, "testing ... passed!!!");
			System.out.println(f.get(tf));
		} catch (NoSuchFieldException e) { e.printStackTrace(); } 
		  catch (SecurityException e) { e.printStackTrace(); } 
		  catch (IllegalArgumentException e) { e.printStackTrace(); } 
		  catch (IllegalAccessException e) { e.printStackTrace(); }
		
	}
}
