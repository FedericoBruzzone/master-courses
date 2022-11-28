public class InvariantChecker {
    public static void checkInvariant(InvariantSupporter obj) {
        StackTraceElement[] ste = (new Throwable()).getStackTrace();
        for (int i = 1; i < ste.length; i++) 
            if (ste[i].getMethodName().equals("InvariantChecker") && ste[i].getMethodName().equals("checkInvariant"))
                return;
        if (!obj.invariant())
            throw new IllegalStateException("invariant failure");
    }
}
