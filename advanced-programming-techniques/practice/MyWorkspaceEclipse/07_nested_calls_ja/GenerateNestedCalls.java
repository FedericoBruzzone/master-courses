import java.io.IOException;
import javassist.*;

// javac -cp /usr/share/java/javassist.jar GenerateNestedCalls.java
// java -cp .:/usr/share/java/javassist.jar GenerateNestedCalls
// java Main

public class GenerateNestedCalls {
	public static void main(String[] args) {
		ClassPool cp = ClassPool.getDefault();
		try {
			// ===== MyHandler =====
			cp.importPackage("java.lang.invoke.MethodHandle");
			cp.importPackage("java.lang.invoke.MethodHandles");
			cp.importPackage("java.lang.reflect.InvocationHandler");
			cp.importPackage("java.lang.reflect.Method");
			cp.importPackage("java.lang.reflect.Proxy");
			
			CtClass myHandler = cp.makeClass("MyHandler", cp.get("NestedCalls"));
			myHandler.addInterface(cp.get("java.lang.reflect.InvocationHandler"));
			myHandler.addField(CtField.make("NestedCallsI target;", myHandler));
			myHandler.addField(CtField.make("int counter;", myHandler));
			myHandler.addConstructor(CtNewConstructor.make("public MyHandler() {"
					+ "counter = 0;"
					+ "target = (NestedCallsI)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), this);"
					+ "}", myHandler));
			myHandler.addMethod(CtNewMethod.make("public String repeat(String str, int times) {"
					+ "StringBuilder sb = new StringBuilder(str.length() * times);"
					+ "for (int i = 0; i < times; i++) {"
					+ "sb.append(str);"
					+ "}"
					+ "return sb.toString();"
					+ "}", myHandler));
			myHandler.addMethod(CtNewMethod.make("public int a() { return target.a(); }", myHandler));
			myHandler.addMethod(CtNewMethod.make("public int b(int a) { return target.b(a); }", myHandler));
			myHandler.addMethod(CtNewMethod.make("public int c(int a) { return target.c(a); }", myHandler));
			myHandler.addMethod(CtNewMethod.make("public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {"
					+ "Method superM = NestedCalls.class.getDeclaredMethod(m.getName(), m.getParameterTypes());"
					+ "counter++;"
					+ "System.out.println(repeat(\"#\", counter)+\" \"+m.getName());"
					+ "MethodHandle newM = MethodHandles.lookup().unreflectSpecial(superM, getClass());"
					+ "Object res = newM.bindTo(this).invokeWithArguments(args);"
					+ "counter--;"
					+ "return res;"
					+ "}", myHandler));
			myHandler.writeFile();
			
			// ===== Main =====
			CtClass main = cp.makeClass("Main");
			main.addMethod(CtNewMethod.make("public static void main(String[] args) {"
					+ "MyHandler proxy = new MyHandler();"
					+ "System.out.println(\"a() :- \" + proxy.a());"
					+ "System.out.println(\"b(a()) :- \" + proxy.b(proxy.a()));"
					+ "System.out.println(\"c(b(a())) :- \" + proxy.c(proxy.b(proxy.a())));"
					+ "}", main));
			main.writeFile();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
