import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javassist.*;

public class Adapter implements Translator {
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        System.out.println("\tAdapter: START start method ... ");

        CtClass sb = pool.get("java.lang.StringBuilder");
        sb.defrost();
        System.out.println("\t" + sb.getDeclaredMethods().length);
        // List<CtMethod> methods = new ArrayList<CtMethod>(Arrays.asList(sb.getDeclaredMethods()));
        // for (var i : methods) { sb.removeMethod(i); }
        
        //sb.setModifiers(Modifier.clear(sb.getModifiers(), Modifier.FINAL));
        CtClass[] x = new CtClass[]{pool.get("boolean")};
        CtMethod fakeAppend = sb.getDeclaredMethod("append", x);
        fakeAppend.insertAfter("System.out.println(\"APPEND\");\n");

        try {
            sb.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\t" + sb.getDeclaredMethods().length);
        System.out.println("\t" + StringBuilder.class.getDeclaredMethods().length);

        System.out.println("\tAdapter: STOP start method ... ");
    }

    public void onLoad(ClassPool p, String cn) throws NotFoundException, CannotCompileException {
        System.out.println("\tAdapter: START onLoad method ... " + cn);
          
        CtClass sb = p.get("java.lang.StringBuilder");
        System.out.println("\t" + sb.getDeclaredMethods().length);

        System.out.println("\tAdapter: STOP onLoad method ... ");
    }
}
