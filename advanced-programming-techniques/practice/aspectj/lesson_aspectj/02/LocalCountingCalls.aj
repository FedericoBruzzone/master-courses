import java.util.Vector;

public aspect LocalCountingCalls {

 public int FigureElement.counter = 0;
 private Vector<FigureElement> fe = new Vector<FigureElement>();

 pointcut calls(FigureElement fe):
  target(fe) && call(* *.*(..));

 pointcut create():
  call(public *.new(..));

 pointcut ends():
  execution(public static void *.main(String[]));

 before(FigureElement fe): calls(fe) /*&& !within(LocalCountingCalls)*/ {
  fe.counter += 1;
 }

 after() returning(FigureElement obj): create() {
  fe.addElement(obj);
 }

 after(): ends() {
  for(FigureElement f: fe) {
   System.out.println("### Figure " + f + " has called " + f.counter + " methods!");
  }
 }

}
