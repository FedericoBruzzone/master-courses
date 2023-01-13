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
		execution(* *.increment(..)); // use call for pertarget

	after(I i): trackIncrement(i) {
		// System.out.println(MyAspect.aspectOf()); // for cflow & cflowbelow
		if (i.field >= 5) {
			System.out.println(i + "(" + i.field + ")" + " is greater then 5");
		} else {
			System.out.println(i + "(" + i.field + ")" + " is lower then 5");
		}
	}

}
