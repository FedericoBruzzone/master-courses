import java.lang.reflect.*;

// javac *.java
// java Main

public class Main {

	public static void main(String[] argv) {
		NestedCallsI proxy = new MyHandler();

		System.out.println("a() :- " + proxy.a());
		System.out.println("b(a()) :- " + proxy.b(proxy.a()));
		System.out.println("c(b(a())) :- " + proxy.c(proxy.b(proxy.a())));
	}
}