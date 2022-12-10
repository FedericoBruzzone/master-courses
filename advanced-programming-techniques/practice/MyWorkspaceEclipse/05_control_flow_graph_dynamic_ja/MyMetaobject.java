import javassist.tools.reflect.Metaobject;

public class MyMetaobject extends Metaobject {
	public MyMetaobject(Object self, Object[] args) {
		super(self, args);
	}
	
//	@Override
//	public Object trapFieldRead(String arg0) {
//		return super.trapFieldRead(arg0);
//	}
//
//	@Override
//	public void trapFieldWrite(String arg0, Object arg1) {
//		super.trapFieldWrite(arg0, arg1);
//	}

	@Override
	public Object trapMethodcall(int arg0, Object[] arg1) throws Throwable {
		// TODO
		return super.trapMethodcall(arg0, arg1);
	}

}
