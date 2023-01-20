public privileged aspect MyAspect {

	pointcut allMethod():
	  call(* *.*(..)); 
		

	Object around(): allMethod() {
    return proceed();
	}

}
