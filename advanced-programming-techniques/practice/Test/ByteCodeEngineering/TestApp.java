import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javassist.*;

public class TestApp {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // ClassPool pool = ClassPool.getDefault();
        // CtClass sb = pool.get("java.lang.StringBuilder");
        // sb.defrost();
        // System.out.println("\t" + sb.getDeclaredMethods().length);
        // List<CtMethod> methods = new ArrayList<CtMethod>(Arrays.asList(sb.getDeclaredMethods()));
        // for (var i : methods) { sb.removeMethod(i); }
        // //sb.writeFile();
        
        // System.out.println("\t" + sb.getDeclaredMethods().length);
        // System.out.println("\t" + StringBuilder.class.getDeclaredMethods().length);

        System.out.println();
        System.out.println("\t\tTestApp: START main method... ");
        StringBuilder tsb = new StringBuilder();
        //tsb.append(new char[]{'a','b'});
        tsb.append(true);
        System.out.println("\t\t" + tsb.getClass().getDeclaredMethods().length);
        System.out.println("\t\t" + tsb);
        System.out.println("\t\tTestApp: STOP main method... ");
    }
}
