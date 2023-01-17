public class RB implements Resource {
  public RB() {
    System.out.println("I'm creating an RB instance");
  }

  public void destroy() {
    System.out.println("I'm releasing an RB instance");
  }
}
