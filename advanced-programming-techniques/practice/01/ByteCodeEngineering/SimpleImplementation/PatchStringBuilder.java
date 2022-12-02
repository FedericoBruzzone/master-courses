import javassist.*;

// javac -cp /usr/share/java/javassist.jar *.java
// java -cp .:/usr/share/java/javassist.jar PatchStringBuilder
// jar cf StringBuilder.jar java/lang/StringBuilder.class 
// java -cp .:/usr/share/java/javassist.jar --patch-module java.base=StringBuilder.jar MyClass

public class PatchStringBuilder {
	public static void main(String[] args) throws Exception {
		ClassPool classPool = ClassPool.getDefault();
		CtClass sb = classPool.get("java.lang.StringBuilder");
		sb.defrost();
		CtMethod sbAppend = sb.getDeclaredMethod("append", new CtClass[]{classPool.get("char[]")});
		CtMethod sbInsert= sb.getDeclaredMethod("insert", new CtClass[]{CtClass.intType, classPool.get("java.lang.String")});
		
		
		sb.addField(new CtField(CtClass.longType, "startAppend", sb));
		sb.addField(new CtField(CtClass.longType, "endAppend", sb));
		sb.addField(new CtField(CtClass.longType, "startInsert", sb));
		sb.addField(new CtField(CtClass.longType, "endInsert", sb));
		
		sbAppend.insertBefore("{startAppend = System.currentTimeMillis();" +
		                       "System.out.println(\"startAppend: \" + startAppend);}");
		sbAppend.insertAfter("{endAppend = System.currentTimeMillis();" +
                               "System.out.println(\"endAppend: \" + endAppend);" +
				               "long diff = endAppend - startAppend;" +
                               "System.out.println(diff);}");
		sbInsert.insertBefore("{startInsert = System.currentTimeMillis();" +
                			  "System.out.println(\"startInsert: \" + startInsert);}");
		sbInsert.insertAfter("{endInsert = System.currentTimeMillis();" +
                             "System.out.println(\"endInsert: \" + endInsert);" +
                             "long diff = endInsert - startInsert;" +
                             "System.out.println(diff);}");
		
		sb.writeFile();
	}
}
