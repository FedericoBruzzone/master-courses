import java.lang.reflect.*;
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
    TestingFields cls = new TestingFields(7, 3.14);
    Field field = cls.getClass().getDeclaredField("s");
    System.out.println(field.get(cls));
    // field.setAccessible(true);
    field.set(cls, "testing ... passed!!!");
    System.out.println(field.get(cls));
  }
}