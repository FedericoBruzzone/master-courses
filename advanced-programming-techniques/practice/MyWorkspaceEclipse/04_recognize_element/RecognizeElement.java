import java.util.Arrays;

public class RecognizeElement {
	
	public static void recognize(String[] classes, String[] methodsAndFields) {
		int allDeclared = 0;
		try {
			for (String mf : methodsAndFields) {
				for (String cls : classes) {
					Class<?> c = Class.forName(cls);
					String[] c_m = Arrays.stream(c.getDeclaredMethods()).map(m -> m.toString()).toArray(String[]::new);
					String[] c_f = Arrays.stream(c.getDeclaredFields()).map(f -> f.toString()).toArray(String[]::new);
					
					if (Arrays.asList(c_m).contains(mf)) {
						allDeclared++;
						System.out.println("The method " + mf + " is declared in " + c.getName());
					} else if (Arrays.asList(c_f).contains(mf)) {
						allDeclared++;
						System.out.println("The field " + mf + " is declared in " + c.getName());
					} 
				}
			}
			System.out.println("All are declared in one of the classes: " + (allDeclared == methodsAndFields.length));
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
}
