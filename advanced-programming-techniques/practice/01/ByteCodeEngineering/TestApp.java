public class TestApp {
    public static void main(String[] args) {
        System.out.println("Start true main");
        StringBuilder sb = new StringBuilder();
        sb.append(new char[]{'a','b'});
        System.out.println(sb.getClass().getDeclaredMethods().length);
        System.out.println(sb);
        System.out.println("End true main");
    }
}
