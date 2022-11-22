import java.util.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class MainControlFlowGraph {
    
    public static void main(String[] args) throws Exception {
        List<EdgeOfGraph> edges = new ArrayList<EdgeOfGraph>();
        List<Class<?>> classes = new ArrayList<Class<?>>();

        for (String clazz : args) { classes.add(Class.forName(clazz)); }

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz.getName());
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println("\tMethod: " + method);
                if (method.getDeclaredAnnotations() != null) {
                    for (Annotation annotation : method.getDeclaredAnnotations()) {
                        System.out.println("\t\tAnnotation: " + annotation);
                        System.out.println((GraphAnnotation)annotation.clazz());
                    }
                }
            }
        }
    }
}


