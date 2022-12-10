public class Worker implements WorkerI {

    private int workerId;
    private double monthSalary;
    private double yearSalary;
    
    public Worker(int workerId, double salary, boolean monthSalary) {
        this.workerId = workerId;
        if(monthSalary) {
            this.monthSalary = salary;
            this.yearSalary = salary * 12;
        } else {
            this.monthSalary = salary / 12;
            this.yearSalary = salary;        
        }
    }  
    
    @Override
    public int workerId() {
        return this.workerId;
    }
    
    @Override    
    public double monthSalary(){
        return this.monthSalary;
    }
    
    @Override    
    public double yearSalary(){
        return this.yearSalary;
    }
}
