import java.util.*;

privileged public aspect RefMyAspect {

  Set<Object> targets = new HashSet<Object>();

  public pointcut trackMain():
	  execution(public static void *.main(String[]));

  public pointcut create(I i):
		target(i) && 
		call(* *.*(..)) &&
		!within(RefMyAspect);

	before(I i): create(i) {
	  targets.add(i);
	}

	after(): trackMain() {
		for (Object t : targets) {
			MyAspect m_c = MyAspect.aspectOf(t);
			System.out.println(m_c + " " + t.getClass() + " " + m_c.counter);
		}
	}

}
