package java_reflection;

import java.lang.reflect.Method; 
import java.util.stream.*;
import java.util.Arrays;

class MOP_java_lang__reflect_method {  // meta-object protocol
    public static String formalParametersToString(Method m) {
        var method = Arrays.asList(m.getParameterTypes());
        
        return IntStream.range(0, m.getParameterCount())
                        .boxed()
                        .map(i -> { return MOP_java_lang_class.classNameToString(method.get(i))+" p"+(i+1);})
                        .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> new_class = Class.forName("java.util.stream.IntStream");
         
        Stream<Method> methods  = Arrays.asList(new_class.getDeclaredMethods())
                                        .stream()
                                        .filter(s -> s.getName() == "iterate");
        methods.forEach(method -> 
            System.out.println(method.getName()+": "+formalParametersToString(method)));
    }
}
