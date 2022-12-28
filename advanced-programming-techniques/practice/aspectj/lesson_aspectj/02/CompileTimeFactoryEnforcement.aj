public aspect CompileTimeFactoryEnforcement {
 
 pointcut illegalNewFigElt():
  (call(Point.new(..)) || call(Line.new(..))) &&
   !withincode(* FigureFactory.make*(..));

 declare error: 
	 illegalNewFigElt(): "Use factory method instead.";

}
