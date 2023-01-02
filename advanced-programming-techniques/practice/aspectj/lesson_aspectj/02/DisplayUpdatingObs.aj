aspect DisplayUpdatingObs extends Observing {

 declare parents: FigureElement implements Subject;
 declare parents: Display       implements Observer;

 pointcut move(Subject s):
  target(s) &&  (
	execution(void FigureElement+.moveBy(int,int)) ||
	execution(void Line.setP1(Point))              ||
	execution(void Line.setP2(Point))              ||
	execution(void Point.setX(int))                ||
	execution(void Point.setY(int)));

 pointcut changes(Subject s):
  move(s) && !cflowbelow(move(Subject));

 public void Subject.notifyObserver(Subject s, Observer o) {
  ((Display)o).update();
 }

}
