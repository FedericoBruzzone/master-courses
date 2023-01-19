public aspect MyAspect {
 
  pointcut test():
		call(* Main.a(int));

	void around(): test() {
    proceed(100);
	}

}
