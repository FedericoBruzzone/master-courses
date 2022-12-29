import java.io.*;
import javassist.*;

public aspect MyAspect {
 
 pointcut hello_stringBuilder(java.lang.StringBuilder sb):
	target(sb) && call(* *..*.append(char[]));

 after(StringBuilder sb) returning: hello_stringBuilder(sb) { 
  try {
	 ClassPool cp = ClassPool.getDefault();
	 CtClass c_sb = cp.get("java.lang.StringBuilder");
	 c_sb.setModifiers(Modifier.clear(c_sb.getModifiers(), Modifier.FINAL));
   c_sb.writeFile();

   // RUNTIME COMMAND
	 Runtime rt = Runtime.getRuntime();
   Process pr = rt.exec("jar cf StringBuilder.jar java/lang/StringBuilder.class");
	 Process patchStringBuilder = rt.exec("java --patch-module java.base=StringBuilder.jar");
	} catch (NotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
		catch (CannotCompileException e) { e.printStackTrace(); }
 }   

}
