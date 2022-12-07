public class A implements Y {
    public void x() {
        System.out.println("A::x");
        y();
    }

    @Override public void y() {
        System.out.println("??????");
    }
}
