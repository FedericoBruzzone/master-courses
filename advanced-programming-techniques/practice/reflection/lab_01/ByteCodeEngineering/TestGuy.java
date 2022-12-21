import java.io.PrintStream;

import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.bytecode.InstructionPrinter;

//javac -cp .;javassist.jar;bcel-6.5.0.jar newes9lab1/*.java
//java -cp .;javassist.jar;bcel-6.5.0.jar newes9lab1.SBInjector
//jar -cmvf newes9lab1/META-INF/MANIFEST.MF newes9lab1/StringBuilder.jar java/lang/StringBuilder.class
//java -cp .;javassist.jar;bcel-6.5.0.jar --patch-module java.base=newes9lab1/StringBuilder.jar newes9lab1.DurationCalculatorTest
public class TestGuy {

    public static void main(String[] args) throws Throwable {
        
        ClassPool cp = ClassPool.getDefault();
        CtClass sbClass = cp.get("java.lang.StringBuilder");
        //CtClass clazz = cp.get("newes9lab1.SBInjector");

        sbClass.defrost();

        CtField f1 = new CtField(CtClass.longType, "startAppendTime", sbClass);
        f1.setModifiers(Modifier.PRIVATE);
        sbClass.addField(f1);
        CtField f2 = new CtField(CtClass.longType, "endAppendTime", sbClass);
        f2.setModifiers(Modifier.PRIVATE);
        sbClass.addField(f2);
        CtField f3 = new CtField(CtClass.longType, "appendTime", sbClass);
        f3.setModifiers(Modifier.PUBLIC);
        sbClass.addField(f3);
        CtField f4 = new CtField(CtClass.longType, "startInsertTime", sbClass);
        f4.setModifiers(Modifier.PRIVATE);
        sbClass.addField(f4);
        CtField f5 = new CtField(CtClass.longType, "endInsertTime", sbClass);
        f5.setModifiers(Modifier.PRIVATE);
        sbClass.addField(f5);
        CtField f6 = new CtField(CtClass.longType, "insertTime", sbClass);
        f6.setModifiers(Modifier.PUBLIC);
        sbClass.addField(f6);

        CtMethod appendMethod = sbClass.getDeclaredMethod("append", new CtClass[]{cp.get("char[]")});
        CtMethod insertMethod = sbClass.getDeclaredMethod("insert", new CtClass[]{CtClass.intType, cp.get("java.lang.String")});

        //InstructionPrinter.print(appendMethod, new PrintStream(System.out));

        //appendMethod.insertBefore("{for(int i = 0; i < this.getClass().getName().toCharArray().length; i++) {" +
        //                                " System.out.println(this.getClass().getName().toCharArray()[i]);" +
        //                            " }}");
        appendMethod.insertBefore("{System.out.println(\"\\nRegistering start append\"); startAppendTime = System.currentTimeMillis();}");
        appendMethod.insertAfter("{System.out.println(\"\\nRegistering end append\"); endAppendTime = System.currentTimeMillis(); appendTime = endAppendTime - startAppendTime;}");

        insertMethod.insertBefore("{System.out.println(\"\\nRegistering start insert\"); startInsertTime = System.currentTimeMillis();}");
        insertMethod.insertAfter("{System.out.println(\"\\nRegistering end insert\"); endInsertTime = System.currentTimeMillis(); insertTime = endInsertTime - startInsertTime;}");
        
        InstructionPrinter.print(appendMethod, new PrintStream(System.out));

        //sbClass.setModifiers(Modifier.PUBLIC);
        sbClass.setModifiers(Modifier.clear(sbClass.getModifiers(), Modifier.FINAL));
        sbClass.writeFile();
    }
}
