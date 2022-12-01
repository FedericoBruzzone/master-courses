import javassist.*;
import java.io.IOException;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar MyTransformer
// jar cf StringBuilder.jar java/lang/StringBuilder.class
// jar cf SubStringBuilder.jar SubStringBuilder.class
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar MyTestClass

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar MyTransformer
// jar cf ModStringBuilder.jar ModStringBuilder.class
// jar cf StringBuilder.jar StringBuilder.class
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar MyTestClass

public class MyTransformer {
  public static void main(String[] args) throws IOException, NotFoundException, CannotCompileException {
    ClassPool pool = ClassPool.getDefault();
    CtClass sb = pool.get("java.lang.StringBuilder");
    sb.defrost();
    sb.setModifiers(Modifier.clear(sb.getModifiers(), Modifier.FINAL));
    sb.writeFile();

    try {
      CtClass subStringBuilder = pool.makeClass("SubStringBuilder", sb);
      subStringBuilder.addField(CtField.make("public long startAppend;", subStringBuilder));
      subStringBuilder.addField(CtField.make("public long endAppend;", subStringBuilder));

      CtMethod subAppend = CtNewMethod.make(
          "public StringBuilder append(char[] str) {\n" +
              "startAppend = System.currentTimeMillis();\n" +
              "System.out.println(startAppend);\n" +
              "StringBuilder res = super.append(str);\n" +
              "endAppend = System.currentTimeMillis();\n" +
              "System.out.println(endAppend);\n" +
              "long tmp = endAppend - startAppend;\n" +
              "System.out.println(\"Diff: \" + tmp);\n" +
              "return res;\n" +
              "}\n", subStringBuilder);

      subStringBuilder.addMethod(subAppend);
      //subStringBuilder.setName("StringBuilder");
      subStringBuilder.writeFile();

      // Runtime rt = Runtime.getRuntime();
      // Process pr = rt.exec("java -jar map.jar time.rel test.txt debug");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
