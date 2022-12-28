public aspect FactoryEnforcement {
 
 pointcut illegalNewFigElt():
	(call(Point.new(..)) || call(Line.new(..))) &&
   !withincode(* FigureFactory.make*(..));
   
 before(): illegalNewFigElt() {
  throw new Error("Use factory method instead.");
 }

}
