public aspect MyAspect {

  /*pointcut c_to_a():*/
    /*this(C) && call(* A.*(..));*/
  
  /*void around(): c_to_a() {*/
  /*}*/

  public pointcut track(A a): 
    cflowbelow(call(* C.*(..)) && cflow(call(* B.*(..)))) && 
    !within(MyAspect) && 
    !this(A) &&
    target(a);
 
   before(A a): track(a) {
    System.out.println("[ERROR]");
  }

}
