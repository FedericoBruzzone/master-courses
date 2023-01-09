public aspect MyAspect {

  pointcut c_to_a():
	  this(C) && call(* A.*(..));

	void around(): c_to_a() {
    /*System.out.println("before");*/
		/*proceed();	*/
		/*System.out.println("after");	*/
	}

}
