import java.util.ArrayList;
import java.util.Arrays;

public class RecognizingElements {
    String[] class_name;
    Object[] field_method;

    public RecognizingElements(String[] class_name) throws SecurityException, ClassNotFoundException {
        ArrayList<String> tmp = new ArrayList<String>();
        for (String s : class_name) {
            var f = Class.forName(s).getDeclaredFields();
            var m = Class.forName(s).getDeclaredMethods();
            for (var i : f) { tmp.add(i.getName()); }
            for (var i : m) { tmp.add(i.getName()); }
        }
        this.class_name = class_name;
        this.field_method = tmp.toArray();
    }

    public Boolean checkField() {
        Boolean state = true;
        ArrayList<String> tmp = new ArrayList<String>();
        for (Object cls : this.class_name) {
            var cls_methods = cls.getClass().getDeclaredMethods();
            var cls_fields = cls.getClass().getDeclaredFields();
            for (var i : cls_methods) { tmp.add(i.getName()); }
            for (var i : cls_fields) { tmp.add(i.getName()); }

            for (Object element : this.field_method) {
                if (!Arrays.asList(tmp.toArray()).contains(element) || !Arrays.asList(tmp.toArray()).contains(element)) {
                    state = false;
                    break;
                }
            }
            if (state == true) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        RecognizingElements re = new RecognizingElements(new String[] { "java.lang.String" });
        System.out.println(re.field_method[0]);
        System.out.println(re.checkField());
    }
}
