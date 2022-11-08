import java.lang.reflect.Method; 
import java.util.stream.*;
import java.util.Arrays;

class MOP_java_lang_reflect_method {  // meta-object protocol
    public static String formalParametersToString(Method m) {
        var method = Arrays.asList(m.getParameterTypes());
        
        return IntStream.range(0, m.getParameterCount())
                        .boxed()
                        .map(i -> { return MOP_java_lang_class.classNameToString(method.get(i))+" p"+(i+1);})
                        .collect(Collectors.joining(", "));
    }

    public static String headerSuffixToString(Method m) {
        String result = MOP_java_lang_class.classNameToString(m.getReturnType())
                                           + " " + m.getName()
                                           + "(" + formalParametersToString(m) + ")";
    
        Class<?>[] exceptions = m.getExceptionTypes();
        if (exceptions.length > 0) 
            result += " throws " + MOP_java_lang_class.classArrayToString(exceptions);
        
        return result;
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> new_class = Class.forName("java.util.stream.IntStream");
         
        Stream<Method> methods  = Arrays.asList(new_class.getDeclaredMethods())
                                        .stream()
                                        .filter(s -> s.getName() == "iterate"); 
                            
        methods.forEach(method -> 
            System.out.println(method.getName()+": "+formalParametersToString(method)));
        
        System.out.println("----------");
            
        Class<?> new_class_2 = Class.forName("java.lang.reflect.Method");
        Stream<Method> methods_2 = Arrays.asList(new_class_2.getDeclaredMethods())
                                         .stream()
                                         .filter(s->s.getName()=="invoke");
        
        methods_2.forEach(method -> 
            System.out.println(method.getName()+": "+headerSuffixToString(method)));

    }
}
