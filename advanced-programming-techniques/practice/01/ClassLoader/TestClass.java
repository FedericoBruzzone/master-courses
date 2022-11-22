import java.lang.reflect.*;

public class TestClass  {
    String testString = new String("testString");   
    ClassLoader x;

    public TestClass() throws Exception {
        x = testString.getClass().getClassLoader();
    }
    
    public static void main(String[] args) {
        
    }
}
