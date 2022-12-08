import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class MyTransform implements ClassFileTransformer {
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		try {
			ClassPool p = ClassPool.getDefault();
			CtClass c = p.get(className);
			
			p.importPackage("java.lang.annotation.Annotation");
			p.importPackage("java.lang.reflect.Method");
			
			System.out.println("kdsjfhdsifbuy");

			c.addMethod(CtMethod.make("public void buildGraph() { for (Method m : this.getClass().getDeclaredMethods()) { }}", c));
//			"Annotation[] annotations = m.getAnnotations();" +
//			"for (Annotation a : annotations) {" +
//				"if(a.annotationType().equals(AnnotationGraph.class)) {" +
//					"AnnotationGraph ann = (AnnotationGraph)a;" +
//					"for (Class<?> ann_cls : ann.value()) {" +
//						"Graph.addEdge(this.getClass().getName(), m.getName(), ann_cls.getName());" +
//					"}" +
//				"}" +
//			"}" +
//		"}" +
//	"}", c));
	
			return c.toBytecode();
		} catch (NotFoundException e) { e.printStackTrace(); } 
		  catch (IOException e) { e.printStackTrace(); } 
		  catch (CannotCompileException e) { e.printStackTrace(); } 
		
		return classfileBuffer;
	}
}
