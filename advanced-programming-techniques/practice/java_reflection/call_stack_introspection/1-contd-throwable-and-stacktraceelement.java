package call_stack_introspection;

class ABC {
    public void a() {
        b();
    }

    public void b() {
        c();
    }

    private void c() {
        for(StackTraceElement f : new Throwable().getStackTrace())
            System.out.println(f);
    }

    public static void main(String args[]) {
        new ABC().a();
    }
}