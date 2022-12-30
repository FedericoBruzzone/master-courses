public aspect DisplayUpdating {

 pointcut move(FigureElement fe): 
  target(fe) && call(void FigureElement.moveBy(int, int));

 pointcut topLevelMove(FigureElement fe): 
  move(fe) && !cflowbelow(move(FigureElement));

 after(FigureElement fe) returning: topLevelMove(fe) {
  Display.update();
 }

}
