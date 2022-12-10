public class A {
	@AnnotationGraph({B.class})
	public void _x() {
		System.out.println("A::_x");
	}
	
	@AnnotationGraph({B.class})
	public void _y() {
		System.out.println("A::_y");
	}
}
