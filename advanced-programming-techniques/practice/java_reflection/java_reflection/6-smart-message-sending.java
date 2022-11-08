package java_reflection;

import java.lang.reflect.Method;

interface SmartMessageSending { // public
    default public Object receive(String selector, Object[] args) throws Exception {
        Method method = null; 
        Class<?>[] classes = null;

        if (args != null) {
            classes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) 
                classes[i] = args[i].getClass();
        }

        method = this.getClass().getMethod(selector, classes);
        return method.invoke(this, args);
    }
}

class Employee_message implements SmartMessageSending {
    private String name;

    public Employee_message(String name) {
        this.name=name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Employee: "+this.name;
    }
}

class AccessibilityCheck_message {
    public static void main(String[] args) throws Exception {
        try {
            Employee_message mike = new Employee_message("Mike");
            System.out.println(mike.receive("getName", null));
            System.out.println(mike);
            System.out.println(mike.receive("setName", new Object[]{"Eleonor"}));
            System.out.println(mike);
        } catch(NoSuchFieldException | SecurityException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}