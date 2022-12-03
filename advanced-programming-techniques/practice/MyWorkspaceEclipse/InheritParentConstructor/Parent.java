public final class Parent {
	public String x;
	public String y;
	
	public Parent() {
		this.x = new String();
		this.y = new String();
	}
	
	public Parent(String x) {
		this.x = x;
		this.y = new String();
	}
	
	public Parent(String x, String y) {
		this.x = x;
		this.y = y;
	}
}
