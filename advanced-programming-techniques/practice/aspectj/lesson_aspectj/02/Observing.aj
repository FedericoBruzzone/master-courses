import java.util.Vector;

abstract aspect Observing {

 protected interface Subject {}
 protected interface Observer {}

 public Vector<Observer> Subject.observers = new Vector<Observer>();

 public void Subject.addObserver(Subject s, Observer o) {
  s.observers.add(o);
 }

 public void Subject.removeObserver(Subject s, Observer o) {
  s.observers.remove(o);
 }
 
 public Vector<Observer> Subject.getObserver(Subject s) {
  return s.observers;
 } 

 abstract pointcut changes(Subject s);

 after(Subject s): changes(s) {
  for (Observer obs : s.getObserver(s)) {
   s.notifyObserver(s, obs);	
	}
 }
 
 abstract public void Subject.notifyObserver(Subject s, Observer o);

}
