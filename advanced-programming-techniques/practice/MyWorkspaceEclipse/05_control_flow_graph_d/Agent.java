import java.lang.instrument.Instrumentation;

public class Agent {
	public static void premain(String agentArgs, Instrumentation instrumentation) {
		System.out.println("-----Agent::premain-----");
		MyTransform my = new MyTransform();
		instrumentation.addTransformer(my);
	}
}
