public aspect DisplayUpdating {
  pointcut move():
    call(void Line.setP1(Point)) || call(void Line.setP2(Point));
  
  //after() returning: move() { System.out.println("test"); }
}
