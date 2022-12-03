public class SetStackTrace {
    public static void setStackTrace() {
        Throwable t = new Throwable();
        StackTraceElement n = new StackTraceElement("SetStackTrace", "ex", "SetStackTrace", 9);
        t.setStackTrace(new StackTraceElement[]{n});
        t.printStackTrace();
    }

    public static int ex() {
        return 10 / 0;
    }

    public static void recursion(int x) {
        System.out.println(x);
        setStackTrace(); 
        System.out.println(x);
    }

    public static void main(String[] args) {
        recursion(1);
    }
}
