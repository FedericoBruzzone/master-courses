package tsp.module.employee;

public class ReflectiveEmployeeMain {
    public static void main(String[] args) throws Exception {
        Employee angela = new Employee("Angela", "Runedottir");
        System.out.println(angela);
        angela.instVarAtPut("surname", "Odindottir");
        System.out.println(angela);
    }
}