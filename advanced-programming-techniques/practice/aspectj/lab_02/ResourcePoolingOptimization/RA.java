public class RA implements Resource {
  public RA() {
    System.out.println("I'm creating an RA instance");
  }

  public void destroy() {
    System.out.println("I'm releasing an RA instance");
  }
}
