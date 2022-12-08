import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class A {
	@AnnotationGraph({B.class})
	public void _x() {
		System.out.println("A::_x");
	}
	
	@AnnotationGraph({B.class})
	public void _y() {
		System.out.println("A::_y");
	}
}

//private void buildGraph() {
//	for (Method m : this.getClass().getDeclaredMethods()) {
//		Annotation[] annotations = m.getAnnotations();
//		for (Annotation a : annotations) {
//			if(a.annotationType().equals(AnnotationGraph.class)) {
//				AnnotationGraph ann = (AnnotationGraph)a;
//				for (Class<?> ann_cls : ann.value()) {
//					Graph.addEdge(this.getClass().getName(), 
//						          m.getName(), 
//						          ann_cls.getName());
//				}
//			}
//		}
//	}
//}