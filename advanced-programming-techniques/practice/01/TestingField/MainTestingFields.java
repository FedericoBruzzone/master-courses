import java.lang.reflect.*;
import java.util.Date;


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