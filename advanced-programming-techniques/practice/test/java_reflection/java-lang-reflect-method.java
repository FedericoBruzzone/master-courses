package java_reflection;

import java.lang.reflect.Method; 
import java.util.stream.*;
import java.util.Arrays;

import java_reflection.MOP;

class MOP {  // meta-object protocol
    public static String formalParametersToString(Method m) {
        var mt = Arrays.asList(m.getParameterTypes());
        return IntStream.range(0, m.getParameterCount()).boxed()
        .map(i -> {return MOP.classNameToString(mt.get(i))+" p"+(i+1)}).collect(Collectors.joining(", "));
    }
}
