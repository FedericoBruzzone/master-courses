public class TailException extends Exception {

  public int v1 = 0;
  public int v2 = 0;

  public TailException(int v1, int v2) {
    this.v1 = v1;
    this.v2 = v2;
  }
}
