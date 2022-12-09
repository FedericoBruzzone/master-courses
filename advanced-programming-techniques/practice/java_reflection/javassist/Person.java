import javassist.tools.reflect.Metalevel;
import javassist.tools.reflect.Metaobject;

public class Person {
    public String name;
    public static int birth = 3;

    public Person(String name, int birthYear) {
        this.name = name;
        birth = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getAge(int year) {
        return year - birth;
    }

    public static void main(String[] args) {
        Person p = new Person(args[0], 1948);
        System.out.println("name: " + p.getName());
        System.out.println("object: " + p.toString());
        // change the metaobject of p.
        if (p instanceof Metalevel) {
            ((Metalevel) p)._setMetaobject(new Metaobject(p, null));
            System.out.println("«the metaobject was changed.»");
        }
        System.out.println("age: " + p.getAge(2018));
    }
}