import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

class MyExprEditor extends ExprEditor {

//	@Override
//	public void edit(MethodCall m) throws CannotCompileException {
//		if (!m.getClassName().startsWith("java")) {
//			System.out.println(m.getClassName());
//			m.replace("for(int i = 0; i<10; i++) {System.out.println(i);} $_ = $proceed($$);");
//		}
//	}
	
	@Override
	public void edit(MethodCall m) throws CannotCompileException {
			try {
				Object[] annotations = m.getMethod().getAnnotations();
				for (Object annotation : annotations) {
					Annotation tmp = (Annotation)annotation;
					if (tmp.annotationType().equals(AnnotationGraph.class)) {
						for (Class<?> ann : ((AnnotationGraph)tmp).value()) {
							Graph.addEdge(m.getClassName(), m.getMethodName(), ann.getName());
						}
					}
				}
			} catch (ClassNotFoundException e) {e.printStackTrace();} 
			  catch (NotFoundException e) {e.printStackTrace();}
	}
}

public class MyTransform implements ClassFileTransformer {
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		try {
			if (!className.startsWith("java")) {
				ClassPool p = ClassPool.getDefault();
				CtClass c = p.get(className);
				
				c.instrument(new MyExprEditor());
				
				return c.toBytecode();
			}
		} catch (NotFoundException e) { e.printStackTrace(); } 
		  catch (IOException e) { e.printStackTrace(); } 
		  catch (CannotCompileException e) { e.printStackTrace(); } 
		
		return classfileBuffer;
	}
}
