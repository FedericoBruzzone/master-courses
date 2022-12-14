import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class MOP_java_lang_class { // meta-object protocol
    public static String classNameToString(Class<?> cls) {
        if (!cls.isArray())
            return cls.getName();
        else
            return cls.getComponentType().getName() + "[]";
    }

    public static Class<?>[] getAllSuperClasses(Class<?> cls) {
        List<Class<?>> result = new ArrayList<Class<?>>();
        
        for (Class<?> x = cls; x != null; x = x.getSuperclass()) 
            result.add(x);
        return result.toArray(new Class<?>[0]);
    }

    // My Methods
    public static String classArrayToString(Class<?>[] cls_list) {
        String result = new String();
        for (Class<?> cls : cls_list) {
            result += cls.getName() + ", ";
        }
        return result.substring(0, result.length()-2);
    }

    public static String classNameToModifier(Class<?> cls) {
        return Modifier.toString(cls.getModifiers());
    }


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> new_class = Class.forName("java.lang.String");
        System.out.println(new_class);
        System.out.println(classNameToString(new_class));
        System.out.println(classNameToModifier(new_class));

        System.out.println("----------");

        System.out.println(classNameToString(String.class));

        System.out.println("----------");

        Integer[] a = new Integer[]{1,2,3};
        System.out.println(classNameToString(a.getClass()));

        System.out.println("----------");

        for (var superclass : getAllSuperClasses(java.util.ArrayList.class))
            System.out.println(superclass);

//         System.out.println(args[0]);
//         for (int i = 0; i < args.length; i++) {
//             System.out.println(args[i]);
//         }
    }
}