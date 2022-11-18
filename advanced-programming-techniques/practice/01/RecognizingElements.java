import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class RecognizingElements {
    public String[] classes; 
    public String[] fields_methods; 

    public RecognizingElements(String[] classes) throws Exception { 
        this.classes = classes;

        ArrayList<String> tmp = new ArrayList<String>();
        for (String cls : this.classes) {
            Class<?> c_cls = Class.forName(cls);
            for (Method m : c_cls.getDeclaredMethods()) {
                tmp.add(m.getName());
            }
            for (Field f : c_cls.getDeclaredFields()) {
                tmp.add(f.getName());
            }
        }
        //tmp.add("ciao");
        
        this.fields_methods = tmp.toArray(String[]::new);
    } 

    public Boolean checkFieldsMethodsIn() throws Exception {
        List<String> method = new ArrayList<String>();
        List<String> field = new ArrayList<String>();
        
        for (String cls : this.classes) {
            Class<?> c_cls = Class.forName(cls);
            method.addAll(Stream.of(c_cls.getDeclaredMethods())
                .map(m -> m.getName())
                .toList());
            field.addAll(Stream.of(c_cls.getDeclaredFields())
                .map(f -> f.getName())
                .toList());
        }
        for (String f_m : this.fields_methods) {
            if (!(method.contains(f_m) || field.contains(f_m))) {
                return false;
            }
        }
        return true;
    }

    public void whereFieldsMethods() throws Exception {
        List<String> method = new ArrayList<String>();
        List<String> field = new ArrayList<String>();

        for (String cls : this.classes) {
            Class<?> c_cls = Class.forName(cls);
            method.addAll(Stream.of(c_cls.getDeclaredMethods())
                .map(m -> m.getName())
                .toList());
            field.addAll(Stream.of(c_cls.getDeclaredFields())
                .map(f -> f.getName())
                .toList());

            for (String f_m : this.fields_methods) {
                if ((method.contains(f_m) || field.contains(f_m))) {
                    System.out.println(f_m + " is in " + cls);
                }
            }
        }
    }

    public void whatIsFieldsMethods() throws Exception {
        List<String> method = new ArrayList<String>();
        List<String> field = new ArrayList<String>();

        for (String cls : this.classes) {
            Class<?> c_cls = Class.forName(cls);
            method.addAll(Stream.of(c_cls.getDeclaredMethods())
                .map(m -> m.getName())
                .toList());
            field.addAll(Stream.of(c_cls.getDeclaredFields())
                .map(f -> f.getName())
                .toList());

            for (String f_m : this.fields_methods) {
                if (method.contains(f_m)) {
                    System.out.println(f_m + " is a method of \t\t" + cls);
                }
                else if (field.contains(f_m)) {
                    System.out.println(f_m + " is a field of \t\t" + cls);
                }
            }
        }
    }

    public void fullSignatureFieldsMethods() throws Exception {
        List<String> method = new ArrayList<String>();
        List<String> field = new ArrayList<String>();

        for (String cls : this.classes) {
            Class<?> c_cls = Class.forName(cls);
            method.addAll(Stream.of(c_cls.getDeclaredMethods())
                .map(m -> m.getName())
                .toList());
            field.addAll(Stream.of(c_cls.getDeclaredFields())
                .map(f -> f.getName())
                .toList());

            for (String f_m : this.fields_methods) {
                if (method.contains(f_m)) {
                    Stream.of(c_cls.getDeclaredMethods())
                        .filter(i -> i.getName().contains(f_m))
                        .forEach(System.out::println);
                }
                else if (field.contains(f_m)) {
                    Stream.of(c_cls.getDeclaredFields())
                        .filter(i -> i.getName().contains(f_m))
                        .forEach(System.out::println);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        RecognizingElements re = new RecognizingElements(new String[]{"java.lang.String", "java.lang.Integer"});

        System.out.println(re.checkFieldsMethodsIn());
        // re.whereFieldsMethods();
        // re.whatIsFieldsMethods();
        re.fullSignatureFieldsMethods();
    }
}
