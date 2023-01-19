import java.lang.reflect.Method;
import org.aspectj.lang.*;
import org.aspectj.lang.reflect.*;

public privileged aspect MyAspect {

	pointcut trackMethod():
		call(* A.*(..)) &&
		!within(MyAspect);

  void around(): trackMethod() {
		Method ms = ((MethodSignature)thisJoinPointStaticPart.getSignature()).getMethod();
    System.out.println(ms);
    proceed();
	} 

}
