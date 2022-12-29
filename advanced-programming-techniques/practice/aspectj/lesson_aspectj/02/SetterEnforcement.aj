public aspect SetterEnforcement {
 
 pointcut onSet():
	set(Point Line.*) &&
	//!withincode(void Line.set*(Point));
  !within(Line);

 declare error: onSet(): 
	"Use setter method.";

}
