package java_annotation;

import java.lang.annotation.RetentionPolicy ;
import java.lang.annotation.Retention ;

@Retention(RetentionPolicy.RUNTIME)
@interface TODO {
    String value();
}

@TODO("Everything is still missing")
class Test {}

class TestIsAnnotated {
    public static void main(String[] args) {
        Class<?> c = Test.class;
        boolean todo = c.isAnnotationPresent(TODO.class);
        if (todo) 
            System.out.println("The Test class is not completely implemented yet");
        else 
            System.out.println("The Test class is completely implemented");
    }
}