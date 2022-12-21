import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class MyTransform implements ClassFileTransformer {
	
	class AddMyLine extends ExprEditor {
		public void edit(MethodCall m) throws CannotCompileException {
			m.replace("{ System.out.println(\"MyLine\"); }"); //$_ = $proceed($$); 
		}
	}
	
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		System.out.println("MyTransform::transform " + className);
		
		ClassPool cp = ClassPool.getDefault();
		if (className.equals("A")) {
			try {
				CtClass a_cls = cp.get(className);
				for (CtMethod m : a_cls.getDeclaredMethods()) {
					m.instrument(new AddMyLine());
				}
				return a_cls.toBytecode();
			} catch (NotFoundException | CannotCompileException | IOException e) {
				e.printStackTrace();
			}

		}
		return classfileBuffer;
	}
}
