import java.util.*;
import org.aspectj.lang.reflect.*;

public privileged aspect MyAspect {
    HashMap<String, HashMap<List<Object>, Object>> memoizator = new HashMap<String, HashMap<List<Object>, Object>>();

    private pointcut memo():
        within(Main) &&
        /*cflow(execution(public static void *.main(..))) &&*/
        // withincode(@Main.Memoizable * *.*(..)) &&
        // within(@Main.Memoizable *) && // Non va
        call(@Main.Memoizable * *.*(..)) &&
        // call(* *.*(..)) &&
        !call(void *.*(..)) &&
        !call(* java..*.*(..)); //&& args(arg);

    declare soft : Exception : within(MyAspect);

    Object around(): memo() {
        String signature = thisJoinPointStaticPart.getSignature().toString();
        Object[] arg = thisJoinPoint.getArgs();
        List<Object> collection = Collections.unmodifiableList(Arrays.asList(arg)); 

        // System.out.println(memoizator);

        if (memoizator.containsKey(signature) && memoizator.get(signature).containsKey(collection)) {
            System.out.println("*** Memoizated\n    " + memoizator);
            return memoizator.get(signature).get(collection);
        } else {
            // Object tmp = proceed(arg);
            Object tmp = ((MethodSignature)thisJoinPoint.getSignature()).getMethod().invoke(thisJoinPoint.getTarget(), arg);
            memoizator.putIfAbsent(signature, new HashMap<List<Object>, Object>());
            memoizator.get(signature).put(collection, tmp);
            System.out.println("*** Calculated\n    " + memoizator);
            return tmp;      
        }    
    }    

} 