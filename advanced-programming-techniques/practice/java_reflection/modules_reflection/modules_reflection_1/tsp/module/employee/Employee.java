package tsp.module.employee;

import tsp.module.reflection.*;

class Employee implements SmartFieldAccess {
    private String name;
    private String surname;

    public Employee(String n, String s) {
        this.name = n;
        this.surname = s;
    }

    public String toString() {
        return "Employee: " + this.name + " " + this.surname;
    }
}