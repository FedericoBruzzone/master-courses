import java.lang.reflect.Field;

interface ReflectiveCloning { // public
    default public Object copy() throws Exception {
        Object tmp = this.getClass().getDeclaredConstructor().newInstance();
        Field[] fields = this.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fields[i].set(tmp, fields[i].get(this));
        }

        return tmp;
    }
}

class Employee_cloning implements ReflectiveCloning {
    private String name;

    public Employee_cloning() {
        this.name="Anon"; 
    }

    public Employee_cloning(String name) {
        this.name=name;
    }

    public String toString() {
        return "Employee: "+this.name;
    }
}


class AccessibilityCheck_cloning {
    public static void main(String[] args) throws Exception {
        try {
            Employee_cloning mike = new Employee_cloning("Mike");
            System.out.println(mike);
            var eleonor = mike.copy();
            System.out.println(eleonor);
        } catch(NoSuchFieldException | SecurityException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}