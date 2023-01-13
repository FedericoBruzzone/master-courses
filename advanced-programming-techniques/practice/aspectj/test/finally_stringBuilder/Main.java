import java.lang.reflect.Modifier;

public class Main {

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    sb.append(new char[] {'a', 'b'});
    System.out.println(sb);
    System.out.println(Modifier.isFinal(StringBuilder.class.getModifiers()));
  }
}
