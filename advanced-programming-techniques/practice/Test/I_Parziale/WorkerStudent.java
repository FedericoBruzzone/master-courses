// import java.lang.reflect.Proxy;

public interface WorkerStudent extends WorkerI, StudentI {}

// abstract class WorkerStudentC implements WorkerI, StudentI {
//     WorkerI w;
//     StudentI s;

//     public WorkerStudentC(WorkerI w, StudentI s) {
//         this.w = w;
//         this.s = s;
//     }
// }

// class Main {
//     public static void main(String[] args) {
//         Object ws = Proxy.newProxyInstance(WorkerStudentC.class.getClassLoader(), 
//                                             WorkerStudentC.class.getInterfaces(), ...);
//         ws = (WorkerStudentC)ws;        
//     }
// }