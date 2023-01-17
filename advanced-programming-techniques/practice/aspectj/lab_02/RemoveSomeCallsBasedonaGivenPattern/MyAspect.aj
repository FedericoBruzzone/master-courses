public aspect MyAspect {

  /*pointcut c_to_a():*/
    /*this(C) && call(* A.*(..));*/
  
  /*void around(): c_to_a() {*/
  /*}*/

  public pointcut track(A a): 
    cflowbelow(/*this(C)*/ call(* C.*(..))) && !cflowbelow(call(* B.*(..))) && call(* *.*(..)) && target(a);
 
   void around(A a): track(a) {
    System.out.println(a + " poi");
  }

}
