import java.io.*;

public class CountClassLoading extends ClassLoader {
	String[] directories; // "", testclasses, ...
	public int countJava = 0;
	public int countMy = 0;
	
	public CountClassLoading() {
		super(null);
		directories = new String[]{""};
	}
	
	public CountClassLoading(String path) {
		super(null);
		directories = path.split(";");
	}
	
	public CountClassLoading(ClassLoader parent) {
		super(parent);
		directories = new String[]{""};
	}
	
	public CountClassLoading(String path, ClassLoader parent) {
		super(parent);
		directories = path.split(";");
	}
	
	@Override public void finalize() {
		System.out.println("Finalize");
	}
	
	@Override public Class<?> loadClass(String name) throws ClassNotFoundException { 
		if (!name.startsWith("java.")) {
			System.out.println("Loading class from MyClassLoader... " + name);
			countMy += 1;
			System.out.println("countJava: "+ countJava);
			System.out.println("countMy: "+ countMy);
			return findClass(name);
		} else if (name.startsWith("java.lang.") || name.startsWith("java.util.")) {
			System.out.println("Loading class from MyClassLoader... " + name);
			RuntimeException e =  new RuntimeException();
			e.printStackTrace();
			System.out.println("countJava: "+ countJava);
			System.out.println("countMy: "+ countMy);
			return super.loadClass(name);
			// throw e;
		} else {
			System.out.println("Loading class from ParentClassLoader... " + name);
			countJava += 1;
			System.out.println("countJava: "+ countJava);
			System.out.println("countMy: "+ countMy);
			return super.loadClass(name);
		}
	}
	
	@Override public Class<?> findClass(String name) throws ClassNotFoundException {
		for (String directory : directories) {
			byte[] bytecode = getClassData(directory, name);
			if (bytecode != null) 
				return defineClass(name, bytecode, 0, bytecode.length);
		}
		throw new ClassNotFoundException();
	}
	
	protected byte[] getClassData(String directory, String name) {
		String filename;
		filename = directory.equals("") ? name.replace(".", "/") + ".class" : directory + name.replace(".", "/") + ".class";
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			return fileInputStream.readAllBytes();
		} catch (FileNotFoundException e) { e.printStackTrace(); return null; }
		  catch (IOException e) { e.printStackTrace(); return null; }
	}
}
