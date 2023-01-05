aspect SubIntrospection {

	pointcut sub_int():
		withincode(* Introspection.*(..));

	/*void around(Object t): sub_int(t) {*/
    /*System.out.println("around");*/
		/*if(thisJoinPoint.getTarget() == null ||*/
      /*!thisJoinPoint.getTarget().toString().startsWith("java")) {*/
			/*proceed(t); */
		/*}*/
	/*}*/

  before(): sub_int() {
    System.out.println("sjfgsdbkfg");
	}

}
