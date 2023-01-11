import java.util.Random;

privileged public aspect RandomlyMovePoints {

  static Random rnd = new Random();

	pointcut point(Point p): call(* *.*(..)) && target(p);

	after(Point p): point(p) {
    p.x = rnd.nextInt(50)+1;
    p.y = rnd.nextInt(50)+1;
	}

}
