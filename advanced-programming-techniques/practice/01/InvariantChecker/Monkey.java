public class Monkey implements InvariantSupporter {
    public void hang() {
        InvariantChecker.checkInvariant(this);
        System.out.println("hang");
        InvariantChecker.checkInvariant(this);
    }

    public void screech() {
        InvariantChecker.checkInvariant(this);
        System.out.println("screech");
        InvariantChecker.checkInvariant(this);
    }

    @Override public boolean invariant() {
        screech();
        return true;
    }
}
