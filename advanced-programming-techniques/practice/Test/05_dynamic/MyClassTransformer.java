import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.ByteArrayClassPath;
import javassist.LoaderClassPath;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtMember;
import javassist.Modifier;
import javassist.NotFoundException;

public class MyClassTransformer implements ClassFileTransformer {
    private static final String SEP = "\"";
    private static final String TAG = SEP+"[AGENT]:"+SEP;
    private static String printlnCall(String string){
        return "System.out.println(" + TAG + string + ");";
    }
    
    private ClassPool classPool;

    public MyClassTransformer()
    {
        this.classPool = ClassPool.getDefault();
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
 
        byte[] byteCode = classfileBuffer;
 
        // into the transformer will arrive every class loaded so we filter 
        // to match only what we need , (we exclude Logger to prevent loops)
        if (!className.contains("/") && !className.contains("Logger")) {
 
            try {
                // retrive default Javassist class pool
                ClassPool cp = ClassPool.getDefault();
                // get from the class pool our class with this qualified name
                CtClass cc = cp.get(className.replace("/","."));
                // get all the methods of the retrieved class
                CtMethod[] methods = cc.getDeclaredMethods();
                for(CtMethod meth : methods) {
                    // The instrumentation code to be returned and injected
                    StringBuffer preExecutionCode = new StringBuffer();
                    StringBuffer postExecutionCode = new StringBuffer();
                    String name = meth.getName();
                    // just print into the buffer a log for example
                    //buffer.append("Logger.get().log(\"inside "+className+"."+name+"()\");");
                    //buffer.append(printlnCall(SEP+" Method " + name + " terminated executon"+SEP));
                    if(Modifier.isStatic(meth.getModifiers())){                        
                        //preExecutionCode.append(printlnCall(SEP+" From Class: " + className +SEP));
                        //preExecutionCode.append("Logger.get().trackExecution("+SEP+className+SEP+","+SEP+name+SEP+", 0);");
                    }else{
                        preExecutionCode.append("Logger.get().trackExecution("+SEP+className+SEP+","+SEP+name+SEP+", this.hashCode());");
                        postExecutionCode.append("Logger.get().trackPopStack();");
                    }
                    if(name.equals("main")){
                        postExecutionCode.append(
                        "{" +
                            "Logger.get().log(\"Program execution terminated...\");" +
                            "Logger.get().printExecutionGraph();"+
                        "}");
                    }
                    meth.insertBefore(preExecutionCode.toString());
                    meth.insertAfter(postExecutionCode.toString());
                }
                // create the byteclode of the class
                byteCode = cc.toBytecode();
                // remove the CtClass from the ClassPool
                cc.detach();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
 
        return byteCode;
    }


}
