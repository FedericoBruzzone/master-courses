import java.util.Arrays;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MainCFG {
	public static void main(String[] args) {
		GraphCFG<Class<?>, Method> g = new GraphCFG<Class<?>, Method>();
		
		Class<?>[] classes = Arrays.stream(args).map(c -> { 
			try { 
				return Class.forName(c);
			} catch (ClassNotFoundException e) { e.printStackTrace(); return null; }
			}).toArray(Class<?>[]::new);
		
		
		
		for (Class<?> cls : classes) {
			for (Method m : cls.getDeclaredMethods()) {
				for (Annotation a : m.getDeclaredAnnotations()) {
					AnnotationCFG aCFG = (AnnotationCFG)a;
					for (Class<?> classAnnotated : aCFG.value()) {
						g.addNode(cls, m, classAnnotated);
					}
				}
			}
		}
		
		System.out.println(g);
	}
}
