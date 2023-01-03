import java.lang.reflect.Modifier;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

public class Main {

 public static void main(String[] args) {
 	StringBuilder sb = new StringBuilder();
	sb.append(new char[]{'a', 'b'});
	System.out.println(sb);
  System.out.println(Modifier.isFinal(StringBuilder.class.getModifiers()));
 }

}
