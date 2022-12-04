import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		try {
			Class<?> s = Class.forName("java.lang.String");
			Stream<String> stream;
			stream = Arrays.stream(s.getDeclaredMethods()).map(m -> m.toString());
			stream = Stream.concat(stream, Arrays.stream(s.getDeclaredFields()).map(f -> f.toString()));
			
			String[] classes = new String[]{s.getName()};
			String[] methodsAndFields = (String[])stream.toArray(String[]::new);
			
			RecognizeElement.recognize(classes, methodsAndFields);
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
}
