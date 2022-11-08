// import java.lang.reflect.Field;

// class Employee {
//     private String name;
//     public Employee(String name) {
//         this.name = name;
//     }
// }

// class AccessibilityCheck {
//     public static void main(String[] args) throws Exception {
//         try {
//             Employee mike = new Employee("Mike");
//             Field name = Employee.class.getDeclaredField("name");
//             name.setAccessible(true);
//             System.out.println("Value of name: " + name.get(mike));

//             name.set(mike, "Eleonor");
//             System.out.println("Changed value of name: " + name.get(mike));
//         } catch(NoSuchFieldException | SecurityException | IllegalAccessException e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
