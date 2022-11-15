import java.util.ArrayList;

public class RecognizingElements {
    String[] class_name = new String[]{"java.lang.String", "java.lang.Object"};
    ArrayList<String> field_method = new ArrayList<String>(); 

    public RecognizingElements() throws SecurityException, ClassNotFoundException {
        for (String s : class_name) {
            var f = Class.forName(s).getDeclaredFields();
            var m = Class.forName(s).getDeclaredMethods();
            for (var i : f) {
                field_method.add(i.getName());
            }
            for (var i : m) {
                field_method.add(i.getName());
            }
        }
        String[] res = (String[])field_method.toArray();
    }
}
