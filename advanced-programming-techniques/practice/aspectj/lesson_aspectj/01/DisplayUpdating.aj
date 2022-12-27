/*public aspect DisplayUpdating {*/
	
 /*pointcut move():*/
  /*call(void Line.setP1(Point, ..)) || */
  /*call(void Line.setP2(Point));*/

 /*after() returning: move() { */
  /*System.out.println("DisplayUpdating::after()<-move()"); */
  /*Display.update(); */
 /*}*/

/*}*/

public aspect DisplayUpdating {
 
 pointcut move(FigureElement figElt):
  target(figElt) && // if (target.istanceof(FigureElement))
  (call(void FigureElement.moveBy(int, int)) || 
	 call(void Line.setP1(Point, ..))          || 
	 call(void Line.setP2(Point))              ||
	 call(void Point.setX(int))                ||
	 call(void Point.setY(int)));
	
	after(FigureElement/*Object*/ fe) returning: move(fe) { 
	 /*if(fe instanceof Point) {*/
		/*System.out.println(((Point) fe).x = 11);*/
	 /*}*/

	 System.out.println(fe);
	 System.out.println("DisplayUpdating::after()<-move()"); 
	 Display.update(); 
  }

}

