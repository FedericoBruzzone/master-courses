// From modules_reflection_1 folder
// javac tsp/module/employee/ReflectiveEmployeeMain.java
// java tsp.module.employee.ReflectiveEmployeeMain or java tsp/module/employee/ReflectiveEmployeeMain

package tsp.module.employee;

public class ReflectiveEmployeeMain {
    public static void main(String[] args) throws Exception {
        Employee angela = new Employee("Angela", "Runedottir");
        System.out.println(angela);
        angela.instVarAtPut("surname", "Odindottir");
        System.out.println(angela);
    }
}