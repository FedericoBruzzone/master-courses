import java.util.*;

public privileged aspect MyAspect {
	HashMap<String, HashMap<List<Object>, Object>> memoizator = new HashMap<String, HashMap<List<Object>, Object>>();

	private pointcut memo(Object arg):
		within(Main) && 
		/*cflow(execution(public static void *.main(..))) &&*/
		call(* *.*(..)) &&
		!call(void *.*(..)) && 
		args(arg);

  Object around(Object arg): memo(arg) {
		String signature = thisJoinPointStaticPart.getSignature().toString();
    /*Object[] arg = thisJoinPoint.getArgs();*/
    List<Object> collection = Collections.unmodifiableList(Arrays.asList(arg)); 

		System.out.println(memoizator);

		if (memoizator.containsKey(signature) && memoizator.get(signature).containsKey(collection)) {
      System.out.println("Memoizated");  
			return memoizator.get(signature).get(collection);
		} else {
       System.out.println("Calculated");  
       Object tmp = proceed(arg);
		   memoizator.put(signature, new HashMap<List<Object>, Object>());
       memoizator.get(signature).put(collection, tmp);
       return tmp;      
		}	
	}	

} 
