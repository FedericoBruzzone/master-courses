public aspect Introspection {

  pointcut trackAllCall():
    call(* *.*(..)) && !within(Introspection);

  pointcut trackAllExecution():
    execution(* *.*(..)) && !within(Introspection);

  pointcut trackGet():
    get(* *.*) && !within(Introspection);

  pointcut trackSet():
    set(* *.*) && !within(Introspection);

  pointcut trackCallConstructor():
    call(new(..)) && !within(Introspection);
  
  pointcut trackExecutionConstructor():
    execution(new(..)) && !within(Introspection);

	/*after(): trackAllCall() {*/
		/*System.out.println("Instrospection::trackAllCall :- after " + thisJoinPoint.getTarget());*/
	/*}*/

  /*before(): trackAllCall() {*/
    /*System.out.println("Instrospection::trackAllCall :- before " + thisJoinPoint.getTarget());*/
  /*}*/

  after(): trackAllExecution() {
    System.out.println("Instrospection::trackAllExecution :- after " + thisJoinPoint.getTarget());
  }

	/*before(): trackAllExecution() {*/
		/*System.out.println("Instrospection::trackAllExecution :- before " + thisJoinPoint.getTarget());*/
	/*}*/
	
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

