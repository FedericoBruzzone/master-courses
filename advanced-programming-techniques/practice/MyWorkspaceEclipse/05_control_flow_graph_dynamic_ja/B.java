public class B {
	@AnnotationGraph({ A.class })
	public void _x() {
		System.out.println("B::_x");
	}

	@AnnotationGraph({ A.class })
	public void _y() {
		System.out.println("B::_y");
	}
}
