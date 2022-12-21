import java.io.IOException;

import javassist.*;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar GenerateProxy

public class GenerateProxy {
    public static void createProxy() throws CannotCompileException {
        ClassPool p = ClassPool.getDefault();
        try {
            p.importPackage("java.lang.invoke.MethodHandle");
			p.importPackage("java.lang.invoke.MethodHandles");
			p.importPackage("java.lang.reflect.InvocationHandler");
			p.importPackage("java.lang.reflect.Method");
			p.importPackage("java.lang.reflect.Proxy");

            CtClass myProxy = p.makeClass("MyProxy", p.get("NestedCalls"));
            myProxy.defrost();
            myProxy.addInterface(p.get("java.lang.reflect.InvocationHandler"));

            CtField target = CtField.make("public NestedCallsI target = (NestedCallsI)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), this);", myProxy);
            myProxy.addField(target);

            CtMethod invoke = CtMethod.make(
                "public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {"+
                    "System.out.println(method.getName());"+
                    "Method m = NestedCalls.class.getDeclaredMethod(method.getName(), method.getParameterTypes());"+
                    "MethodHandle mh = MethodHandles.lookup().unreflectSpecial(m, getClass());"+
                    "Object res = mh.bindTo(this).invokeWithArguments(args);"+
                    "return res;"+
                "}", myProxy);
            myProxy.addMethod(invoke);

            CtMethod a = CtMethod.make("public int a() { return target.a(); }", myProxy);
            myProxy.addMethod(a);
            CtMethod b = CtMethod.make("public int b(int a) { return target.b(a); }", myProxy);
            myProxy.addMethod(b);
            CtMethod c = CtMethod.make("public int c(int a) { return target.c(a); }", myProxy);
            myProxy.addMethod(c);

            myProxy.writeFile();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void createClassTest() throws CannotCompileException {
        ClassPool p = ClassPool.getDefault();
        p.clearImportedPackages();
        try {
            p.importPackage("java.lang.invoke.MethodHandle");
			p.importPackage("java.lang.invoke.MethodHandles");
			p.importPackage("java.lang.reflect.InvocationHandler");
			p.importPackage("java.lang.reflect.Method");
			p.importPackage("java.lang.reflect.Proxy");

            CtClass myClass = p.makeClass("MyClass");
            CtConstructor main = CtNewConstructor.make(
                "public MyClass(){"+
                    "NestedCallsI pnc = (NestedCallsI)Proxy.newProxyInstance(NestedCalls.class.getClassLoader(), NestedCalls.class.getInterfaces(), new MyProxy());"+
                    "System.out.println(\"NestedCalls a() :- \" + pnc.a());"+
                    "System.out.println(\"NestedCalls b() :- \" + pnc.b(pnc.a()));"+
                    "System.out.println(\"NestedCalls c() :- \" + pnc.c(pnc.b(pnc.a())));"+
                "}", myClass);

            myClass.addConstructor(main);
            myClass.writeFile();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args) throws CannotCompileException, IOException {
        createProxy();
        createClassTest();
        ClassPool cp = ClassPool.getDefault();
		try {
			Loader cl = new Loader(cp);
			Class<?> myClass = cl.loadClass("MyClass");			
			myClass.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
