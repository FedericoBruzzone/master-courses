import java.util.ArrayList;
import java.util.List;

class MOP { // meta-object protocol
    public static String classNameToString(Class<?> cls) {
        if (!cls.isArray())
            return cls.getName();
        else
            return cls.getComponentType().getName() + "[]";
    }

    public static Class<?>[] getAllSuperClasses(Class<?> cls) {
        List<Class<?>> result = new ArrayList<Class<?>>();
        for (Class<?> x = cls; x != null; x = x.getSuperclass()) result.add(x) ;
        return result.toArray(new Class<?>[0]);
    }

    public static void main(String[] args) {
        System.out.println(MOP.classNameToString(String.class));

        System.out.println("----------");

        Integer[] a = new Integer[]{1,2,3};
        System.out.println(MOP.classNameToString(a.getClass()));

        System.out.println("----------");

        for (var superclass : MOP.getAllSuperClasses(java.util.ArrayList.class))
            System.out.println(superclass);

        Class type = Class.fromName(args[0]);
        System.out.println(type);


//         System.out.println(args[0]);
//         for (int i = 0; i < args.length; i++) {
//             System.out.println(args[i]);
//         }
    }
}
