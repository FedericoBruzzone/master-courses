public aspect MyAspect {

 pointcut setDoubleArgs(int a, int b):
  call(void A.setDoubleArgs(int,int)) && args(a, b);

 after(int a, int b): setDoubleArgs(a, b) {
  System.out.println(Integer.toString(a) + " " + Integer.toString(b));
 }

}
