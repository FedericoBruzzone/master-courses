public aspect MyAspect {

	pointcut test():
 		execution(public static void *.main(String[])) &&
  		!within(MyAspect);

 	before(): test() {
  		System.out.println("MyAspect::test");
 	}

 	public void method() {
  		System.out.println("MyAspect::method");
 	}

}