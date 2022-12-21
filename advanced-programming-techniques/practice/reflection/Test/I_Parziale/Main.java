import java.util.*;

public class Main {
    public static void main(String[] args) {
        WorkerStudent ws = Factory.getInstance(3, new String[]{"TSP"}, 29.7, 7, 2000, true);
        System.out.println("Student.studentId() -> " + ws.studentId());
        System.out.println("Student.curricula() -> " + Arrays.toString(ws.curricula()));
        System.out.println("Student.avarange() -> " + ws.avarange());
        System.out.println("Student.workerId() -> " + ws.workerId());
        System.out.println("Student.monthSalary() -> " + ws.monthSalary());
        System.out.println("Student.yearSalary() -> " + ws.yearSalary());                        

        System.out.println("");

        StudentI st = ws;
        System.out.println("Student.studentId() -> " + st.studentId());
        System.out.println("Student.curricula() -> " + Arrays.toString(st.curricula()));
        System.out.println("Student.avarange() -> " + st.avarange());

        System.out.println("");
                
        WorkerI wr = ws;
        System.out.println("Student.workerId() -> " + wr.workerId());
        System.out.println("Student.monthSalary() -> " + wr.monthSalary());
        System.out.println("Student.yearSalary() -> " + wr.yearSalary());                        
        
        System.out.println("");
        System.out.println("");
        
        ws = Factory.getInstance(new Student(3, new String[]{"TSP"}, 29.7), new Worker(7, 2000, true));
        System.out.println("Student.studentId() -> " + ws.studentId());
        System.out.println("Student.curricula() -> " + Arrays.toString(ws.curricula()));
        System.out.println("Student.avarange() -> " + ws.avarange());
        System.out.println("Student.workerId() -> " + ws.workerId());
        System.out.println("Student.monthSalary() -> " + ws.monthSalary());
        System.out.println("Student.yearSalary() -> " + ws.yearSalary());                        

        System.out.println("");

        st = ws;
        System.out.println("Student.studentId() -> " + st.studentId());
        System.out.println("Student.curricula() -> " + Arrays.toString(st.curricula()));
        System.out.println("Student.avarange() -> " + st.avarange());

        System.out.println("");
                
        wr = ws;
        System.out.println("Student.workerId() -> " + wr.workerId());
        System.out.println("Student.monthSalary() -> " + wr.monthSalary());
        System.out.println("Student.yearSalary() -> " + wr.yearSalary());                        
    }    
}
