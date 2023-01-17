public class RC implements Resource {
  public RC() {
    System.out.println("I'm creating an RC instance");
  }

  public void destroy() {
    System.out.println("I'm releasing an RC instance");
  }
}
