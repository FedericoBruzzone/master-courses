// ajc -1.9 WhereDoesTheTimeGo.aj -outxml -outjar timing.jar
// java -javaagent:/home/federicobruzzoneplasma/aspectj1.9/lib/aspectjweaver.jar -classpath timing.jar:$CLASSPATH Simple


public aspect WhereDoesTheTimeGo {

	private int nesting = 0;
  
	pointcut executions(): 
		execution(* *(..)) && !within(WhereDoesTheTimeGo) ;

	Object around(): executions() {
    nesting++;
    long stime = System.currentTimeMillis();
    Object o = proceed();
    long etime = System.currentTimeMillis();
    nesting--;
    StringBuilder info = new StringBuilder();
    for (int i=0; i<nesting; i++) info.append(" ");
    info.append(thisJoinPoint+" took "+(etime-stime)+"ms");
    System.out.println(info.toString());
    return o;
	}

}
