public aspect ExceptionLogging {

 pointcut excpt(): execution(* *..*.*(..));

 after() throwing(Throwable e): excpt() {
  System.out.println("EXCT: " + e);
	for(StackTraceElement f : e.getStackTrace()) 
	 System.out.println("  - " + f.getClassName() + "\n" + f.getMethodName());
 }

}
