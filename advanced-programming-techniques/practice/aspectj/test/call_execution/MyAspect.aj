public aspect MyAspect {
 
 pointcut onCall(Test t, int a):
	target(t) &&
  call(* *.*(..)) &&
	args(a);

 pointcut onExecution(Test t, int a):
	target(t) &&	
	execution(* *.*(..)) &&
	args(a); 

 before(Object t, int a): onCall(t, a) {
	System.out.println("before::onCall");
	System.out.println("Target: " + t);
	System.out.println("Args: " + a); 
 }

 before(Object t, int a): onExecution(t, a) {
	System.out.println("before::onExecution");	
	System.out.println("Target: " + t);
 	System.out.println("Args: " + a);
 }

 after(Object t, int a): onCall(t, a) {
	System.out.println("after::onCall");
	System.out.println("Target: " + t);
	System.out.println("Args: " + a);
 }

 after(Object t, int a): onExecution(t, a) {
	System.out.println("after::onExecution");
	System.out.println("Target: " + t);
	System.out.println("Args: " + a);
 }

}
