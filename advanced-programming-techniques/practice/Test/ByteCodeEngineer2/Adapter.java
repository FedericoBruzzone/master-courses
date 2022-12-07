import javassist.*;

class Adapter implements Translator {
    
    public void start(ClassPool p) {}

    public void onLoad(ClassPool p, String cn) {
        try {
            if (cn.equals("A")) {
                CtClass cls_a = p.get(cn);
                CtMethod a_m = cls_a.getDeclaredMethod("a");
                a_m.insertBefore("System.out.println(\"MyLine\");");
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}