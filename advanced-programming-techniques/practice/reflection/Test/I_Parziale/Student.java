public class Student implements StudentI {

    private int studentId;
    private String[] curricula;
    private double avarange;

    public Student(int studentId, String[] curricula, double avg) {
        this.studentId = studentId;
        this.curricula = curricula;
        this.avarange = avg;
    }

    @Override
    public int studentId() {
        return this.studentId;
    }
    
    @Override
    public String[] curricula(){ 
        return this.curricula;
    }
    
    @Override
    public double avarange(){
        return this.avarange;
    }
}
