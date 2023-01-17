public privileged aspect MyAspect {

  public pointcut trackCall(int x):
    call(* A+.*(..)) &&
    args(x)         &&
    !within(MyAspect);

  int around(int x): trackCall(x) {
    return proceed(proceed(x));
  }
}
