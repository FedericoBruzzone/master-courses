import java.lang.StackTraceElement;

aspect ExceptionLogging { 

 //pointcut excpt(): execution(* *..*.*(..)); // for package 
 pointcut excpt(): execution(* *.*(..)); // void Point.setX(int)

 after() throwing(Throwable e): excpt() {
  System.out.println("EXCT: "+e);
  for (StackTraceElement f: e.getStackTrace())
   System.out.println(" · "+f.getClassName()+"\n   · "+f.getMethodName());
 }

}
