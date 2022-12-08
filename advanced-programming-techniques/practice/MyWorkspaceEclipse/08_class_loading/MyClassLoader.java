import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	static private Integer myCount = 0;
	static private Integer javaCount = 0;
	
	public MyClassLoader() 					 { super(); }
	public MyClassLoader(ClassLoader parent) { super(parent); }
	
	private void print() {
		System.out.println("myCount: " + myCount);
		System.out.println("javaCount: " + javaCount);
	}
	
	public Class<?> loadClass(String name) {
		try {
			System.out.println(name);
			if (!name.startsWith("java.")) {
				myCount++;
				print();
				return this.findClass(name);
			}
			javaCount++;
			if (name.startsWith("java.util.") || name.startsWith("java.lang.")) {
				print();
				new RuntimeException().printStackTrace();
			}
			print();
			return super.loadClass(name);
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
		return null;
	}
	
	public Class<?> findClass(String name) {
		try {
			String path = name.replace(".", "/") + ".class";
			FileInputStream fi = new FileInputStream(path);
			byte[] b = fi.readAllBytes();
			return defineClass(name, b, 0, b.length);
		} catch (FileNotFoundException e) { e.printStackTrace(); } 
		  catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}
