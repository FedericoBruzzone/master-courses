public class CallStackTest {
    public static void hello() throws Exception { checkStack(); System.out.println("hello"); }
    public static void say() throws Exception { fake(); hello(); }

    public static void checkStack() throws Exception {
        StackTraceElement[] ste = (new Throwable()).getStackTrace();
        Boolean check = false;
        for (StackTraceElement e : ste) {
            System.out.println(e);
            if (check && !e.getMethodName().equals("main")) {
                throw new Exception();
            }
            if (e.getClassName().equals("CallStackTest") && e.getMethodName().equals("hello")) {
                check = true;
            }
        }
    } 

    public static void fake() {
        Throwable t = new Throwable();
        StackTraceElement[] ste = new StackTraceElement[t.getStackTrace().length+1];
        ste[ste.length - 1] = new StackTraceElement("CallStackTest", "main", "CallStackTest", 9);
        t.setStackTrace(ste);
        t.printStackTrace();
    }


    public static void main(String[] args) throws Exception {
        hello();
        say();
    }
}
