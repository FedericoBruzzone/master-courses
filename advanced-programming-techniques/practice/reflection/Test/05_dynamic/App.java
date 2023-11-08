public class App{
    private static AppComponent component;

    public static void main(String[] args){
        System.out.println("I'm main inside App");
        component = new AppComponent(47);

        component.setVal(55);
        component.getVal();
        if(args.length > 0)
            new AppController(Integer.parseInt(args[0])).iterateOver();
    }
}
