public aspect PointCoordinateTracing {

 pointcut onTracing(int newVal):
	set(int Point.*) && 
	args(newVal);

 before(int newVal): onTracing(newVal) {
  System.out.println("At " + thisJoinPoint.getSignature() + " field is set to " + newVal + "." + " " + thisJoinPoint.getSourceLocation().toString());
 }

}
