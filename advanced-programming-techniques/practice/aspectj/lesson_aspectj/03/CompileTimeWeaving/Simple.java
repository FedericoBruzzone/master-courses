public class Simple {

	public static void countSlow(int value) { 
		count(value,5); 
	}
  
	public static void countFast(int value) { 
		count(value,0); 
	}
  
	private static void count(int value, int delay) {
    for (int i=0;i<value;i++)
      try { Thread.sleep(delay); } catch (Exception e) {}
  }

	public static void main(String[]argv) {
    countFast(1000);
    countSlow(1000);
  }

}
