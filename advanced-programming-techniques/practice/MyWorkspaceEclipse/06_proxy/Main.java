import java.lang.reflect.Proxy;

public class Main {
	public static void main(String[] args) throws Exception {
		ITestingFields myTestingFields = new MyTestingFields(10, 1);
		ITestingFields p = (ITestingFields) Proxy.newProxyInstance(
				                         			myTestingFields.getClass().getClassLoader(), 
				                         			myTestingFields.getClass().getInterfaces(), 
				                         			new TraceHandler(myTestingFields));
		p.setAnswer(0);
	}
}
