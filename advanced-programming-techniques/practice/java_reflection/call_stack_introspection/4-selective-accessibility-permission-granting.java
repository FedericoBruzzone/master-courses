package call_stack_introspection;

import java.lang.reflect.Field;
// import java.lang.reflect.ReflectPermission;
// import java.security.Permission;

class Employee {
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        this.name=name;
        this.surname=surname;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurnameC(String surname) {
        this.surname=surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String toString() {
        return "Employee: "+this.name+" "+this.surname;
    }
}

class SelectiveAccessibilityCheck {
    public static void main(String[] args) throws Exception {
        // System.setSecurityManager(new SecurityManager() {
        //     public void checkPermission(Permission p) {
        //         if (p instanceof ReflectPermission && "suppressAccessChecks".equals(p.getName()))
        //             for (StackTraceElement e : Thread.currentThread().getStackTrace())
        //                 if ("SelectiveAccessibilityCheck".equals(e.getClassName()) && "setName".equals(e.getMethodName())) 
        //                     throw new SecurityException();
        //     }
        // });

        Employee eleonor = new Employee("Eleonor", "Runedottir");
        setSurname(eleonor, "Odindottir");
        System.out.println(eleonor);
        setName(eleonor, "Angela");
        System.out.println(eleonor);
    }

    private static void setSurname(Employee e, String s) throws Exception {
        Field surname = Employee.class.getDeclaredField("surname");
        surname.setAccessible(true);
        surname.set(e, s);
    }

    private static void setName(Employee e, String n) throws Exception {
        Field name = Employee.class.getDeclaredField("name");
        name.setAccessible(true); 
        name.set(e, n); 
    }
}

