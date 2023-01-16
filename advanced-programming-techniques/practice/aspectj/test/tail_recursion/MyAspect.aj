public privileged aspect MyAspect {

  public int v1 = 0;
  public int v2 = 0;

  pointcut trackTail(int value1, int value2):
    call(* *.factorial(..)) && args(value1, value2);

  int around(int value1, int value2) throws TailException: trackTail(value1, value2) {
    System.out.println("trackTail" + " " + value1 + " " + value2);
    v1 = value1;
    v2 = value2;
    
    System.out.println((new Throwable()).getStackTrace().length);

    if ((new Throwable()).getStackTrace().length > 3) {
      throw new TailException(value1,value2);
    } else 
      while(true) 
        try {
          return proceed(v1, v2);  
        } catch(TailException te) {
          v1 = te.v1;
          v2 = te.v2;
        }
  }
}
