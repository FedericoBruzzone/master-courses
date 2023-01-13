privileged public aspect MyAspect pertarget(create()){

  public int counter = 0;
  private Object t = null;

  public pointcut create(): 
		execution(I+.new(..));	
 
  public pointcut track(I i):
		target(i) && 
		call(* *.*(..)) &&
		!within(*Aspect);

  after(I i) returning: track(i) {
		if (counter == 0) { t = i; }
		counter++;
	}

}
