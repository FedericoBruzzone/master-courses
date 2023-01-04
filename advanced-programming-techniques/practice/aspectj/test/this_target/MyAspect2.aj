public aspect MyAspect2 {

/*
 pointcut this_call():
	//call(void B.b_test()) &&
	call(* *.*(..)) &&
	within(Main);
*/

 interface IMyTest {}
 declare parents: A implements IMyTest;
 declare parents: B implements IMyTest;
 declare parents: Main implements IMyTest;

 pointcut this_call(IMyTest t):
 	target(t) &&
	call(* *.*(..)) && within(Main);

 before(IMyTest t): this_call(t) {
	//if(thisJoinPoint.getTarget() == null ||
    //  !thisJoinPoint.getTarget().toString().startsWith("java")) {
	System.out.println("before::this_call: ");
	System.out.println("\tthis: " + thisJoinPoint.getThis());
	System.out.println("\ttarget: " + thisJoinPoint.getTarget());
	System.out.println("\tstaticpart: " + thisJoinPoint.getStaticPart().getSourceLocation());
	//}
}

}
