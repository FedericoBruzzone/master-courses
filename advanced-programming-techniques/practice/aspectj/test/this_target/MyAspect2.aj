public aspect MyAspect2 {

 pointcut this_call():
	call(void B.b_test()) &&
	within(Main);

 before(): this_call() {
  //((A)thisJoinPoint.getTarget()).n = 69;
	System.out.println("before::this_call: "); 
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
 }

}
