import java.util.*;
import java.lang.reflect.*;
import org.aspectj.lang.reflect.*;

public privileged aspect MyAspect pertarget(track()) {

  public HashMap<String, ArrayList<Object>> hm = new HashMap<String, ArrayList<Object>>();

  public pointcut track():
		call(* *.*(..)) &&
		/*!withincode(* java..*(..)) &&*/
		!call(* java..*(..)) &&
		!within(MyAspect);

	void around(): track() {
		String sign = thisJoinPoint.getSignature().toString();

		if (hm.containsKey(sign)) {
      Object[] new_args = thisJoinPoint.getArgs();
      Object[] old_args = hm.get(sign).toArray(Object[]::new);
      
			for (int i = 0; i < new_args.length; i++) {
        Class<?> cls = old_args[i].getClass(); 
				if (cls == Integer.class) {
          new_args[i] = (int)old_args[i] + (int)new_args[i]; 
				} else if (cls == String.class) {
          new_args[i] = (String)old_args[i] + (String)new_args[i]; 
				}
			}
      
			hm.remove(sign);

			try {
        Method m = ((MethodSignature)thisJoinPoint.getSignature()).getMethod();
        m.invoke(thisJoinPoint.getTarget(), new_args);
      } catch(Exception e) {}
		} else {
      hm.put(sign, new ArrayList(Arrays.asList(thisJoinPoint.getArgs())));
		}
	}

}
