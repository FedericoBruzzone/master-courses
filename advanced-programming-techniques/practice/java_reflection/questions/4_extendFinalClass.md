```Java
package java_reflection;

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
            Employee_access employee = new Employee_access("Mike");
            System.out.println(employee.instVarAt("name"));

            employee.instVarAtPut("name", "Eleonor");
            System.out.println(employee.instVarAt("name"));
        } catch(NoSuchFieldException | SecurityException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

1. Come estendere una classe final

2. Come accedere ai campi di una classe final con SmartFieldAccess

3. Cambiare `modifier` di una classe

4. Aggiungere una notazione a una classe a runtime
   Aggiungere metodi a una classe (final)
