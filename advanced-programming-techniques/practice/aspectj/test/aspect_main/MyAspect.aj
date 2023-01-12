public aspect MyAspect {

	before(): call(public static void *.main(..)) {
    System.out.println("Before::call");
	}

	public static void main(String[] args) {
		Main.main(null);
	  System.out.println("MyAspect::main");
	}

}
