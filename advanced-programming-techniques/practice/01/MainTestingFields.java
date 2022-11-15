import java.util.Date;

class TestingFields {
    private Double d[];
    private Date dd;
    public static final int i = 42;
    protected static String s = "testing ...";
  
    public TestingFields(int n, double val) {
      dd = new Date();
      d = new Double[n];
      for(int i=0; i<n; i++) d[i] = i*val;
    }
}

class MainTestingFields {
  public static void main(String[] args) throws Exception {
    var t = new TestingFields(7, 3.14);
    var f = TestingFields.class.getDeclaredField("s");
    System.out.println(t.s);
    f.setAccessible(true);
    f.set(t, "testing ... passed!!!");
    System.out.println(t.s);
  }
}