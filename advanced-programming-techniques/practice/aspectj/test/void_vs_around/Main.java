public class Main {

	int mInt() {
    return 10; 
	}
	
	void mVoid() {
	  System.out.println("10");
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.mVoid();
		System.out.println(m.mInt());
	}

}
