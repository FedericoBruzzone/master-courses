public aspect Introspection {

  pointcut trackAllCall():
    call(* *.*(..)) && !within(Introspection) && cflow(execution(public static void *.main(..))); 

  pointcut trackAllExecution():
    execution(* *.*(..)) && !within(Introspection) && cflow(execution(public static void *.main(..)));

  pointcut trackGet():
    get(* *.*) && !within(Introspection) && cflow(execution(public static void *.main(..)));

  pointcut trackSet():
    set(* *.*) && !within(Introspection) && cflow(execution(public static void *.main(..)));

  pointcut trackCallConstructor():
    call(new(..)) && !within(Introspection) && cflow(execution(public static void *.main(..)));
  
  pointcut trackExecutionConstructor():
    execution(new(..)) && !within(Introspection) && cflow(execution(public static void *.main(..)));

  after(): trackAllCall() {
    System.out.println("Instrospection::trackAllCall :- after " + thisJoinPoint.getTarget());
  }

  before(): trackAllCall() {
    System.out.println("Instrospection::trackAllCall :- before " + thisJoinPoint.getTarget());
  }

  after(): trackAllExecution() {
         System.out.println("Instrospection::trackAllExecution :- after " + thisJoinPoint.getTarget());
  }

  before(): trackAllExecution() {
    System.out.println("Instrospection::trackAllExecution :- before " + thisJoinPoint.getTarget());
  }
	
	/*after(): trackCallConstructor() {*/
		/*System.out.println("Instrospection::trackCallConstructor :- after " + thisJoinPoint.getTarget());*/
	/*}*/

	/*before(): trackCallConstructor() {*/
		/*System.out.println("Instrospection::trackCallConstructor :- before " + thisJoinPoint.getTarget());*/
	/*}*/
	
	/*after(): trackExecutionConstructor() {*/
		/*System.out.println("Instrospection::trackExecutionConstructor :- after " + thisJoinPoint.getTarget());*/
	/*}*/

	/*before(): trackExecutionConstructor() {*/
		/*System.out.println("Instrospection::trackExecutionConstructor :- before " + thisJoinPoint.getTarget());*/
	/*}*/
	
	/*after(): trackGet() {*/
		/*System.out.println("Instrospection::trackGet :- after " + thisJoinPoint.getTarget());*/
	/*}*/
	
	/*before(): trackGet() {*/
		/*System.out.println("Instrospection::trackGet :- before " + thisJoinPoint.getTarget());*/
	/*}*/
	
	/*after(): trackSet() {*/
		/*System.out.println("Instrospection::trackSet :- after " + thisJoinPoint.getTarget());*/
	/*}*/

	/*before(): trackSet() {*/
		/*System.out.println("Instrospection::trackSet :- before " + thisJoinPoint.getTarget());*/
	/*}*/

}

