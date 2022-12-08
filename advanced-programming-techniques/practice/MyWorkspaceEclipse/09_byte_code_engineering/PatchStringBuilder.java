import java.io.IOException;
import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.CtMethod;
import javassist.CtNewMethod;

class AddTimer extends ExprEditor {
	@Override
	public void edit(MethodCall m) throws CannotCompileException {
		System.out.println(m.getClassName());
		m.replace("{ long start = System.currentTimeMillis();"
				+ "System.out.println(\"Start: \" + start);"
				+ " $_ = $proceed($$); "
				+ "long end = System.currentTimeMillis();"
				+ "System.out.println(\"End: \" + end);"
				+ "System.out.println(\"Diff: \" + (end - start));"
				+ "}");
	}
}

public class PatchStringBuilder {
	
	public static void main(String[] args) {
		try {
			ClassPool cp = ClassPool.getDefault();
			CtClass sb = cp.get("java.lang.StringBuilder");
			sb.setModifiers(Modifier.clear(sb.getModifiers(), Modifier.FINAL));
			sb.writeFile();
			sb.defrost();
			
			CtClass sbFake = cp.makeClass("FakeStringBuilder", sb);
			
			CtMethod sbAppend = CtNewMethod.make("public abstract StringBuilder append(char[] str);", sbFake);
			sbAppend.setBody("{ StringBuilder res = super.append($1); return res; }");
			sbAppend.instrument(new AddTimer());
			sbFake.addMethod(sbAppend);
			
			CtMethod sbInsert = CtNewMethod.make("public abstract StringBuilder insert(int offset, String str);", sbFake);
			sbInsert.setBody("{ StringBuilder res = super.insert($1, $2); return res; }");
			sbInsert.instrument(new AddTimer());
			sbFake.addMethod(sbInsert);
			
			sbFake.setModifiers(sbFake.getModifiers() & ~Modifier.ABSTRACT);
			
			sbFake.writeFile();
		} catch (NotFoundException e) { e.printStackTrace(); } 
		  catch (IOException e) { e.printStackTrace(); } 
		  catch (CannotCompileException e) { e.printStackTrace(); }
		
	}
}
