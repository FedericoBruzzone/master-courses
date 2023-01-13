privileged public aspect MyAspect
	/*pertarget(create())*/
	/*percflow(track())*/
	/*percflowbelow(track())*/
	perthis(create()) {

	public pointcut create():
		execution(I+.new(..));

	public pointcut track():
		call(* I+.*(..));

	public pointcut trackIncrement(I i):
		target(i) &&
		call(* *.increment(..)); // use execution for perthis

	public pointcut trackIncrementThis():
		this(I) &&
		call(* *.increment(..));

	before(): trackIncrementThis() {
		System.out.println(thisJoinPoint.getThis());
		System.out.println(thisJoinPoint.getTarget());
	}

	after(I i): trackIncrement(i) {
		// System.out.println(MyAspect.aspectOf()); // for cflow & cflowbelow
		if (i.field >= 5) {
			System.out.println(i + "(" + i.field + ")" + " is greater then 5");
		} else {
			System.out.println(i + "(" + i.field + ")" + " is lower then 5");
		}
	}

}