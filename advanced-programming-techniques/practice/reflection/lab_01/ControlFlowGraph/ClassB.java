public class ClassB {
    @GraphAnnotation(clazz={ClassA.class})
    public static void callToClassA() {}
}