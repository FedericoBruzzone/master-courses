import java.lang.reflect.InvocationTargetException;
import javassist.Modifier;
import java.lang.reflect.Field;

public class Test {
	public static void main(String[] args) {
		try {
			System.out.println("---------- Parent ----------");
			Class<?> parent_cls = Class.forName("Parent");
	        Object parent_i = parent_cls.getDeclaredConstructor(new Class<?>[]{java.lang.String.class, java.lang.String.class}).newInstance("x", "y");
			System.out.println("Parent instance class: " + parent_i.getClass());
	        System.out.println("Parent instance modifier: " + Modifier.toString(parent_i.getClass().getModifiers()));
	        Field parent_x = parent_i.getClass().getDeclaredField("x");
	        Field parent_y = parent_i.getClass().getDeclaredField("y");
	        System.out.println("Parent variable x value: " + parent_x.get(parent_i));
	        System.out.println("Parent variable x value: " + parent_y.get(parent_i));
	        System.out.println("-----------------------------");
	        System.out.println("------------ Son ------------");
			Class<?> son_cls = Class.forName("Son");
	        Object son_i = son_cls.getDeclaredConstructor(new Class<?>[]{java.lang.String.class, java.lang.String.class}).newInstance("x", "y");
			System.out.println("Son instance class: " + son_i.getClass());
	        System.out.println("Son instance modifier: " + Modifier.toString(son_i.getClass().getModifiers()));
	        Field son_x = parent_i.getClass().getDeclaredField("x");
	        Field son_y = parent_i.getClass().getDeclaredField("y");
	        System.out.println("Son variable x value: " + son_x.get(son_i));
	        System.out.println("Son variable x value: " + son_y.get(son_i));
	        System.out.println("-----------------------------");
		} catch (InstantiationException e) { e.printStackTrace(); } 
		  catch (IllegalAccessException e) { e.printStackTrace(); } 
		  catch (IllegalArgumentException e) { e.printStackTrace(); } 
		  catch (InvocationTargetException e) { e.printStackTrace(); } 
		  catch (NoSuchMethodException e) { e.printStackTrace(); } 
		  catch (SecurityException e) { e.printStackTrace(); } 
		  catch (ClassNotFoundException e) { e.printStackTrace(); }
		  catch (NoSuchFieldException e) { e.printStackTrace(); }
	}
}
