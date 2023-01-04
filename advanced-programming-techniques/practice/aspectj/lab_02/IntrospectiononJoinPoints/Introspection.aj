public aspect Introspection {

	pointcut trackAllCall():
		call(* *.*(..)) && !within(Introspection);

	pointcut trackAllExecution():
		execution(* *.*(..)) && !within(Introspection);

	after(): trackAllCall() {
		System.out.println("Instrospection::trackAllCall :- after");
	}

	before(): trackAllCall() {
		System.out.println("Instrospection::trackAllCall :- before");
	}

	after(): trackAllExecution() {
		System.out.println("Instrospection::trackAllExecution :- after");
	}

	before(): trackAllExecution() {
		System.out.println("Instrospection::trackAllExecution :- before");
	}

}
