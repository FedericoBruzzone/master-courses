package java_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface GroupTODO2 {
    public enum Severity {CRITICAL, IMPORTANT, TRIVIAL, DOCUMENTATION};
    Severity severity() default Severity.IMPORTANT;
    String item();
    String assignedTo();
}

class Test2 {
    @GroupTODO2 (
        severity = GroupTODO2.Severity.CRITICAL, 
        item = "Figure out the amount of interest per month", 
        assignedTo = "Federico Bruzzone"
    )
    public void calculateInterest(float amount, float rate) {
        // need to finish this method later
    } 
}

class GetMembers {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> c = Test2.class;
        Method element = c.getMethod("calculateInterest", float.class, float.class);
        GroupTODO2 groupTodo = element.getAnnotation(GroupTODO2.class);
        String assignedTo = groupTodo.assignedTo();
        System.out.println("TODO item on Test is assigned to: "+assignedTo+".");
    }
}