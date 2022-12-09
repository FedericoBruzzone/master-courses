import java.io.IOException;
import javassist.*;

public class MyTranslator implements javassist.Translator {
	@Override
	public void start(ClassPool p) throws NotFoundException, CannotCompileException {
		try {
			// ===== Create ITestingFields =====
			CtClass iTestingFields = p.makeInterface("ITestingFields");
			CtMethod setAnswer = CtMethod.make("public void setAnswer(int a);", iTestingFields);
			CtMethod message = CtMethod.make("public String message();", iTestingFields);
			iTestingFields.addMethod(setAnswer);
			iTestingFields.addMethod(message);
			iTestingFields.writeFile();
			
			// ===== Create Proxy =====
			p.importPackage("java.lang.reflect.Method");
			p.importPackage("java.lang.reflect.Proxy");
			
			CtClass traceHandler = p.makeClass("TraceHandler");
			traceHandler.addInterface(p.get("java.lang.reflect.InvocationHandler"));

			CtField target = CtField.make("public Object target;", traceHandler);
			traceHandler.addField(target);
			
			CtConstructor constructor = CtNewConstructor.make("public TraceHandler(Object target) {"
					+ "this.target = target;"
					+ "}", traceHandler);
			traceHandler.addConstructor(constructor);
			
			CtMethod invoke = CtNewMethod.make("public Object invoke(Object _, Method m, Object[] args) throws Throwable {\n"
					+ "System.out.println(\"Invoke\");"
					+ "System.out.println(\"Before: \" + ((TestingFields)target).message());"
					+ "Object res = m.invoke(target, args);"
					+ "System.out.println(\"After: \" + ((TestingFields)target).message());"
					+ "return res;"
					+ "}", traceHandler);
			traceHandler.addMethod(invoke);
			
			traceHandler.writeFile();
			
			// ===== Create Main =====
			CtClass main = p.makeClass("Main");
			CtMethod main_m = CtNewMethod.make("public static void main(String[] args) throws Throwable {"
					+ "TestingFields testingFields = new TestingFields(10, 0.0);"
					+ "ITestingFields tf = (ITestingFields)Proxy.newProxyInstance(TestingFields.class.getClassLoader(), TestingFields.class.getInterfaces(), new TraceHandler(testingFields));"
					+ "tf.setAnswer(0);"
					+ "}", main);
			main.addMethod(main_m);
			main.writeFile();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onLoad(ClassPool p, String cn) throws NotFoundException, CannotCompileException {
		try {
			if (cn.equals("TestingFields")) {
				CtClass testingFields = p.get(cn);
				testingFields.addInterface(p.get("ITestingFields"));
				testingFields.writeFile();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

}
