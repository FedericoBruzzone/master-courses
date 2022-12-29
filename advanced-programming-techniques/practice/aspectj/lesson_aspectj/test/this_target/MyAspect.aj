public aspect MyAspect {

 pointcut this_call():
	call(void A+.*(..));

 pointcut this_execution():
	execution(void A+.*(..));

 before(): this_call() {
  //((A)thisJoinPoint.getTarget()).n = 69;
	System.out.println("before::this_call: "); 
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
 }
  
 after(): this_execution() {
  System.out.println("after::this_execution: "); 
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
 }

 before(): this_execution() {
  System.out.println("before::this_execution: "); 
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
 }
  
 after(): this_call() {
  System.out.println("after::this_call: "); 
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
 }

}
