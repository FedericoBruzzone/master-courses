public class Main extends MyAspect {

    public Main() {
        super();
    }

	public void main_method() {
        super.method();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.main_method();
    }

}