import javassist.*;

import java.lang.reflect.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

public class Adapter implements Translator {
    CtClass myStringBuilder;
    CtClass trueStringBuilder;
    CtMethod myAppend;
    CtMethod trueAppend;
    CtClass[] ctParam;

    public void start(ClassPool p) throws NotFoundException {
        myStringBuilder = p.get("MyStringBuilder");
        trueStringBuilder = p.get("java.lang.StringBuilder");
        
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

            trueAppend = trueStringBuilder.getDeclaredMethod("append", ctParam);
            myAppend = myStringBuilder.getDeclaredMethod("append", ctParam);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void onLoad(ClassPool p, String cn) throws NotFoundException, CannotCompileException {
        // CtClass cls = p.get(cn); 

        CtMethod[] methods = trueStringBuilder.getDeclaredMethods();
        System.out.println(trueStringBuilder.getName());
        for (int i=0; i < methods.length; i++) {
            if (methods[i].getName().equals("append") && Arrays.equals(methods[i].getParameterTypes(), ctParam) && !Modifier.isVolatile(methods[i].getModifiers())) {
                //methods[i] = myAppend;
                trueStringBuilder.removeMethod(methods[i]);
                System.out.println("REMOVE");
                // trueStringBuilder.addMethod(CtNewMethod.copy(myAppend, trueStringBuilder, null));
                // System.out.println("ADD");
            }
        }
        trueStringBuilder.toClass();
        System.out.println(trueStringBuilder.getDeclaredMethods().length);
    }
}
