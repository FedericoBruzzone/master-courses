import java.util.ArrayList;

public class AppController{
    ArrayList<AppComponent> components = new ArrayList<>();

    AppController(int n){
        for (int i=0; i < n;i++){
            components.add(new AppComponent(i));
        }
    }
    
    public void iterateOver(){
        components.stream().forEach(c -> System.out.println(c.getVal()));
    }
}