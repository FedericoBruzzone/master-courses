import java.util.Hashtable;

public aspect CountingCalls {
 
 public Hashtable<FigureElement, java.lang.Integer> counters = new Hashtable();
 
 pointcut calls(FigureElement fe): 
  target(fe) && call(* *.*(..));

 pointcut ends():
	execution(public static void *.main(String[]));

 before(FigureElement fe) : calls(fe) {
	//System.out.println(thisJoinPoint.getThis()); 
	//System.out.println(thisJoinPoint.getTarget()); 
  java.lang.Integer old = counters.get(fe);
  counters.put(fe, (old!=null)?old+1:1);
 }

 after(): ends() {
  for(FigureElement fe : counters.keySet())
	 System.out.println("### Figure "+fe+" has called "+counters.get(fe)+" methods!");
 }

}
