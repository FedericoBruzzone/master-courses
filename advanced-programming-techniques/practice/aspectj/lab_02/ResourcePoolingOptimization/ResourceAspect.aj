public privileged aspect ResourceAspect {

  ResourceManager rm = new ResourceManager();
  
  public pointcut create():
    call(Resource+.new(..));

  public pointcut destroy(Resource t):
    call(* *.destroy(..)) && target(t);

  pointcut exclude(): 
    !within(Resource) && 
    !within(ResourceManager) && 
    !within(ResourceAspect);

  Object around(): create() {
    System.out.println("*** I'm pooling a XXX instance");
    Resource r = rm.getResource(thisJoinPointStaticPart.getSignature().getDeclaringType().toString());
    if (r == null) {
      return proceed();
    } else {
      return r;
    }
  }
  
  after(Resource t): destroy(t) {
    System.out.println("*** I'm storing a RA instance"); 
    rm.releaseResource(thisJoinPointStaticPart.getSignature().getDeclaringType().toString(), t);
  }


}
