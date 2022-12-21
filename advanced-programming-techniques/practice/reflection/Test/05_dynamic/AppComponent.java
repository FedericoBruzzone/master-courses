import java.util.Calendar;
import java.util.Date;

public class AppComponent{
    private Integer config;
    private Date lastUpdate;

    public AppComponent(Integer value){
        this.config = value;
    }

    public int getVal(){
        return config;
    }

    public void setVal(int newVal){
        this.config = newVal;
        this.lastUpdate = this.getDate();
    }
    public Date getDate(){
        return Calendar.getInstance().getTime();
    }
}