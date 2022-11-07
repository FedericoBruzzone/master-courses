import java.lang.reflect.*;

interface SmartFieldAccess { // public
    default public Object instVarAt(String name) throws Exception {
        Field f = this.getClass().getDeclaredField(name);
        f.setAccessible(true);
        if (!Modifier.isStatic(f.getModifiers())) 
            return f.get(this);
        return null;
    }

    default public void instVarAtPut(String name, Object value) throws Exception {
        Field f = this.getClass().getDeclaredField(name);
        f.setAccessible(true);
        if (!Modifier.isStatic(f.getModifiers())) 
            f.set(this, value);
    }
}
class Employee_access implements SmartFieldAccess {
    private String name;
    public Employee_access(String name) {
        this.name=name;
    }
}

class AccessibilityCheck_access {
    public static void main(String[] args) throws Exception {
        try {
            Employee_access mike = new Employee_access("Mike");
            System.out.println(mike.instVarAt("name"));
            mike.instVarAtPut("name", "Eleonor");
            System.out.println(mike.instVarAt("name"));
        } catch(NoSuchFieldException | SecurityException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}