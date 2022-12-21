import java.lang.reflect.*;

class Factory {
    public static WorkerStudent getInstance(int studentId, String[] curricula, double avg, int workerId, double salary, boolean monthSalary) {
        Handler h = new Handler(new Student(studentId, curricula, avg), new Worker(workerId, salary, monthSalary));
        return (WorkerStudent) Proxy.newProxyInstance(h.getClass().getClassLoader(), new Class[]{WorkerStudent.class}, h);
    }
    public static WorkerStudent getInstance(Student st, Worker wr) {
        Handler h = new Handler(st, wr);
        return (WorkerStudent) Proxy.newProxyInstance(h.getClass().getClassLoader(), new Class[]{WorkerStudent.class}, h);
    }
}
