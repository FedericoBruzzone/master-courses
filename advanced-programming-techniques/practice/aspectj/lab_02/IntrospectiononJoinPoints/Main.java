public class Main {

    public static void main(String[] args) {
        System.out.println("### Constructor");
			  A c_a = new A(10);
				System.out.println("### test method");
        c_a.test();
        System.out.println("### set i_a");
				c_a.i_a = 20;
        System.out.println("### get i_a"); 
		    int tmp = c_a.i_a;
		}

}
