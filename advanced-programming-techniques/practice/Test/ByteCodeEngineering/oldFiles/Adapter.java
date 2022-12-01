package oldFiles;
import javassist.*;

import java.lang.reflect.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Adapter implements Translator {.
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {}

    public void onLoad(ClassPool p, String cn) throws NotFoundException, CannotCompileException {
        //CtClass cls = p.get(cn); 
        CtClass myStringBuilder =  p.get("MyStringBuilder");
        CtClass trueStringBuilder = p.get("java.lang.StringBuilder");
        CtClass[] ctParam = null;
        try {
            Method m = StringBuilder.class.getDeclaredMethod("append", new Class<?>[]{char[].class});
            Class<?>[] mParam = m.getParameterTypes();
            ArrayList<CtClass> al = new ArrayList<CtClass>();

            CtClass tmp;
            for (Class<?> mp : mParam ){
                tmp = p.get(mp.getName());
                al.add(tmp);
            }
            ctParam = al.toArray(CtClass[]::new);

            CtMethod trueAppend = trueStringBuilder.getDeclaredMethod("append", ctParam);
            CtMethod myAppend = myStringBuilder.getDeclaredMethod("append", ctParam);
        } catch (Exception e) { e.printStackTrace(); }


        CtMethod[] methods = trueStringBuilder.getDeclaredMethods();
        for (int i=0; i < methods.length; i++) {
            if (methods[i].getName().equals("append") && Arrays.equals(methods[i].getParameterTypes(), ctParam) && !Modifier.isVolatile(methods[i].getModifiers())) {
                //methods[i] = myAppend;
                trueStringBuilder.removeMethod(methods[i]);
                System.out.println("REMOVE");
                // trueStringBuilder.addMethod(CtNewMethod.copy(myAppend, trueStringBuilder, null));
                // System.out.println("ADD");
            }
        }
    
        System.out.println(StringBuilder.class.getDeclaredMethods().length);
        System.out.println(trueStringBuilder.getDeclaredMethods().length);
    }
}
