public class B extends I {

	public void increment() {
        this.field++;
        this.inner();
    }

	public void inner() {
        System.out.println("B::inner");
    }
}
