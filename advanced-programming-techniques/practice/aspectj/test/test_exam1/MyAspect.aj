public privileged aspect MyAspect {

  private pointcut noXMethod(X t):
    cflow(call(* Y.*(..))) && 
	  (call(String I.*(..)) && target(t)) &&
	  !within(MyAspect);

  private pointcut noXMethod2(X t):
    cflow(call(* Y.*(..))) && 
	  (call(int I.*(..)) && target(t)) &&
	  !within(MyAspect);

  String around(X t): noXMethod(t) {
    System.out.println("[ERROR]");
	  return "";	
	}
  
	int around(X t): noXMethod2(t) {
    System.out.println("[ERROR]");
	  return 0;	
	}

}
