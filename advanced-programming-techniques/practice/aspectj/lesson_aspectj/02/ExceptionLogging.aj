import java.lang.StackTraceElement;

aspect ExceptionLogging { 

 //pointcut excpt(): execution(* *..*.*(..)); // for package 
 //pointcut excpt(): execution(* *.*(..)); // void Point.setX(int)
 pointcut excpt(Object o): target(o) && execution(* *.*(..)); 

 after(Object o) throwing(Throwable e): excpt(o) {
  System.out.println("EXCT: "+ e + " " + o.getClass());
  for (StackTraceElement f: e.getStackTrace())
   System.out.println(" · "+f.getClassName()+"\n   · "+f.getMethodName() + " " + f.getLineNumber());
 }
 
}
