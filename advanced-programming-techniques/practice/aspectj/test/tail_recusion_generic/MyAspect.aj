public privileged aspect MyAspect {

  public pointcut trackTail(): 
    call(* *.*(..)) && !within(MyAspect);

  int around()
}
