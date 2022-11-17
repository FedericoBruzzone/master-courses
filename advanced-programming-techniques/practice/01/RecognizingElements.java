import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecognizingElements {
    String[] class_name;
    Object[] field_method;

    public RecognizingElements(String[] class_name) throws SecurityException, ClassNotFoundException {
        ArrayList<String> tmp = new ArrayList<String>();
        for (String s : class_name) {
            var f = Class.forName(s).getDeclaredFields();
            var m = Class.forName(s).getDeclaredMethods();
            for (var i : f) { tmp.add(i.getName()); }
            for (var i : m) { tmp.add(i.getName()); }
        }
        this.class_name = class_name;
        this.field_method = tmp.toArray();
    }

    // All field_method in class_name
    public Boolean checkFieldsAndMethods() {
        ArrayList<String> tmp = new ArrayList<String>();
         for (var cls : class_name) {
            Method[] m = cls.getClass().getDeclaredMethods();
            Field[] f = cls.getClass().getDeclaredFields();
            for (var i : m) { tmp.add(i.getName()); }
            for (var i : f) { tmp.add(i.getName()); }
            for (var f_m : field_method) {
                if (!(Arrays.asList(tmp.toArray()).contains(f_m) || Arrays.asList(tmp.toArray()).contains(f_m))) {
                    return false;
                }
            }
        }
        return true;
    }

    // All field_method in class_name
    public void whereFieldsAndMethods() {
        ArrayList<String> tmp = new ArrayList<String>();
         for (var cls : class_name) {
            Method[] m = cls.getClass().getDeclaredMethods();
            Field[] f = cls.getClass().getDeclaredFields();
            for (var i : m) { tmp.add(i.getName()); }
            for (var i : f) { tmp.add(i.getName()); }
            for (var f_m : field_method) {
                if (Arrays.asList(tmp.toArray()).contains(f_m) || Arrays.asList(tmp.toArray()).contains(f_m)) {
                    System.out.println(f_m + " is declared in " + cls);
                }
            }
        }
    }
    
    // For each field_method is a field or a method
    public void whatFieldsAndMethods() throws NoSuchMethodException, SecurityException {
        ArrayList<String> tmp_m = new ArrayList<String>();
        ArrayList<String> tmp_f = new ArrayList<String>();
        for (String cls : class_name) {
            Method[] m =  cls.getClass().getDeclaredMethods();
            Field[] f = cls.getClass().getDeclaredFields();
            for (var i : m) { tmp_m.add(i.getName()); }
            for (var i : f) { tmp_f.add(i.getName()); }
            for (var f_m : field_method) {
                if (Arrays.asList(tmp_m.toArray()).contains(f_m)) {
                    System.out.println(f_m + " is a method declared in " + cls);
                }
                if (Arrays.asList(tmp_f.toArray()).contains(f_m)) {
                    System.out.println(f_m + " is a field declared in " + cls);
                }
            }
        }
    }

    // For each field_method is a field or a method
    public void signatureFieldsAndMethods() throws NoSuchMethodException, SecurityException, NoSuchFieldException {
        ArrayList<String> tmp_m = new ArrayList<String>();
        ArrayList<String> tmp_f = new ArrayList<String>();
        for (String cls : class_name) {
            Method[] m =  cls.getClass().getDeclaredMethods();
            Field[] f = cls.getClass().getDeclaredFields();
            for (var i : m) { tmp_m.add(i.getName()); }
            for (var i : f) { tmp_f.add(i.getName()); }
            for (var f_m : field_method) {
                if (Arrays.asList(tmp_m.toArray()).contains(f_m)) {
                    try {
                        cls.getClass().getMethod(f_m.toString()).setAccessible(true);
                        System.out.println(cls.getClass().getMethod(f_m.toString()) + " " + cls);
                    } catch (NoSuchMethodException e) {
                        System.out.println("[ERROR]" + e);
                    }
                }
                if (Arrays.asList(tmp_f.toArray()).contains(f_m)) {
                    try {
                        cls.getClass().getField(f_m.toString()).setAccessible(true);
                        System.out.println(cls.getClass().getField(f_m.toString()) + " " + cls);
                    } catch (NoSuchFieldException e) {
                        System.out.println("[ERROR]" + e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, NoSuchFieldException {
        RecognizingElements re = new RecognizingElements(new String[] { "java.lang.String", "java.lang.Integer" });
        System.out.println(re.field_method[0]);
        // System.out.println(re.checkFields());
        System.out.println(re.checkFieldsAndMethods());
        //re.whereFieldsAndMethods();
        //re.whatFieldsAndMethods();
        re.signatureFieldsAndMethods();
    }
}
