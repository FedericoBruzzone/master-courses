/*public aspect DisplayUpdating {*/
  /*pointcut move():*/
    /*call(void Line.setP1(Point)) || call(void Line.setP2(Point));*/
  
  /*after() returning: move() { */
	 /*System.out.println("DisplayUpdating::after()<-move()"); */
	 /*Display.update(); */
 /*}*/
/*}*/

public aspect DisplayUpdating {
	pointcut move(FigureElement figElt):
		target(figElt) &&
		 (call(void FigureElement.moveBy(int, int)) || 
	    call(void Line.setP1(Point))              || 
			call(void Line.setP2(Point))              ||
			call(void Point.setX(int))                ||
			call(void Point.setY(int)));
	
	after(FigureElement fe) returning: move(fe) { 
	 System.out.println(fe);
	 System.out.println("DisplayUpdating::after()<-move()"); 
	 Display.update(); 
 }
}


